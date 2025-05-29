package org.example.springsecuritylearning.springsecurity.Context.controller;

import org.example.springsecuritylearning.springsecurity.Context.service.CacheService;
import org.example.springsecuritylearning.springsecurity.Context.model.CachedResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class ProxyController {

    @Value("${proxy.origin}")
    private String origin;

    private final CacheService cacheService;
    private final WebClient webClient = WebClient.builder().build();

    public ProxyController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @DeleteMapping("/clear-cache")
    public String clearCache() {
        cacheService.clearAll();
        return "Cache Cleared";
    }

    @RequestMapping("/**")
    public Mono<ResponseEntity<byte[]>> proxyCaching(ServerWebExchange exchange, @RequestBody(required = false) Mono<byte[]> bodyMono) {
        String path = exchange.getRequest().getURI().getPath();
        String query = exchange.getRequest().getURI().getQuery();
        HttpMethod method = exchange.getRequest().getMethod();
        if (method == null) method = HttpMethod.GET;
        String cacheKey = path + ":" + (query != null ? query : "");

        // Check cache
        CachedResponse cache = cacheService.get(cacheKey);
        if (cache != null) {
            HttpHeaders header = new HttpHeaders();
            header.putAll(cache.getHeader());
            header.add("X-CACHE", "HIT");
            return Mono.just(new ResponseEntity<>(cache.getBody(), header, HttpStatus.OK));
        }

        // Forming origin URL
        String url = origin + path + (query != null ? "?" + query : "");
        WebClient.RequestBodySpec requestSpec = webClient.method(method).uri(url);

        // Setting headers
        exchange.getRequest().getHeaders().forEach((name, headerValues) -> {
            requestSpec.header(name, headerValues.toArray(new String[0]));
        });
        //Prepare the request to get forwarded to the origin server
        Mono<ClientResponse> responseMono = (bodyMono != null && (method == HttpMethod.PUT || method == HttpMethod.POST || method == HttpMethod.PATCH))
                ? bodyMono.flatMap(body -> requestSpec.bodyValue(body).exchangeToMono(Mono::just))
                : requestSpec.exchangeToMono(Mono::just);


        return responseMono
                .flatMap(response -> response.bodyToMono(byte[].class)
                        .map(responseBody -> {
                            HttpHeaders headers = new HttpHeaders();
                            response.headers().asHttpHeaders().forEach(headers::put);
                            headers.add("X-CACHE", "MISS");

                            if (response.statusCode().is2xxSuccessful()) {
                                cacheService.set(cacheKey, new CachedResponse(responseBody, headers));
                            }
                            return new ResponseEntity<>(responseBody, headers, response.statusCode());
                        })
                )
                .onErrorResume(e -> {
                  //Handling the Exceptions and give assaan response
                    e.printStackTrace();
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("X-CACHE", "ERROR");
                    String errorMsg = "Proxy error: " + e.getMessage();
                    return Mono.just(new ResponseEntity<>(errorMsg.getBytes(), headers, HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }
}
