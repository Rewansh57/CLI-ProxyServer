package org.example.springsecuritylearning.springsecurity.Context.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.springsecuritylearning.springsecurity.Context.model.CachedResponse;
import org.example.springsecuritylearning.springsecurity.Context.service.CacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController


public class ProxyController {
    @Value("${server.origin")
    private String origin;


    private final CacheService cacheService;

    private WebClient webClient;

    ProxyController(CacheService cacheService) {
        this.webClient = WebClient.builder().build();
        this.cacheService = cacheService;


    }

    @RequestMapping("/**")// For any pattern of endpoint directions
    public Mono<ResponseEntity<byte[]>> proxyCaching(ServerWebExchange exchange, @RequestBody(required = false) Mono<byte[]> bodyMono) {
        String path = exchange.getRequest().getURI().getPath();
        String query = exchange.getRequest().getURI().getQuery();
        HttpMethod method = exchange.getRequest().getMethod();
        String cacheKey = path + ":" + query;  //Definition of cacheKey to map the same response on same request


        CachedResponse cache = cacheService.get(cacheKey);
        if (cache != null) {
            HttpHeaders header = new HttpHeaders();
            header.putAll(cache.getHeader());
            header.add("X-CACHE", "HIT"); // Means Cache had the response of this request
            return Mono.just(new ResponseEntity<>(cache.getBody(), header, HttpStatus.OK));


        }
        String url = origin + path + (query != null ? "?" + query : "");

        WebClient.RequestBodySpec requestSpec = webClient.method(method).uri(url);

        exchange.getRequest().getHeaders().forEach((name, headerValues) -> {
            requestSpec.header(name, headerValues.toArray(new String[0]));
        });

        Mono<ClientResponse> responseMono=(bodyMono!=null && (method==HttpMethod.PUT ||method ==HttpMethod.POST || method==HttpMethod.PATCH))?bodyMono.flatMap(body->
                requestSpec.bodyValue(body).exchangeToMono(Mono::just)):
                requestSpec.exchangeToMono(Mono::just);


        return responseMono.flatMap(response-> response.bodyToMono(byte[].class)
                .map(responseBody->
                {
                    HttpHeaders headers=new HttpHeaders();
                    response.headers().asHttpHeaders().forEach((name,headerValues)->{
                        headers.put(name,headerValues);
                    });
                    headers.add("X-CACHE", "MISS");
                    cacheService.set(cacheKey,new CachedResponse(responseBody,headers));
                    return new ResponseEntity<>(cache.getBody(), headers, HttpStatus.OK);





                })
        );





    }


}
