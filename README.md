Spring Boot Caching Proxy:
A command-line tool and server that acts as a caching HTTP proxy. It forwards requests to an origin server, caches responses, and serves cached results for repeated requests. Useful for speeding up repeated API calls, reducing load on the origin, and experimenting with backend proxying in Java using Spring Boot and WebFlux.

Features:
HTTP Proxy: Forwards all requests to a configurable origin server.

Caching: Caches responses in-memory for repeated requests.

Cache Control: Adds X-CACHE: HIT or X-CACHE: MISS headers to indicate cache usage.

Cache Clearing: Clear the cache via CLI or HTTP endpoint.

Configurable: Set proxy port and origin server via command-line or properties.

Error Forwarding: Forwards origin server errors (4xx, 5xx) to clients.

Built with: Java, Spring Boot, WebFlux, Picocli.


Made with:
Java 21
Maven 

Build:
mvn clean package

Run the Proxy Server:

java -jar target/SpringSecurity-0.0.1-SNAPSHOT.jar --port=9001 --origin=https://dummyjson.com
--port: Port for the proxy server (default: 8080 if not specified).

--origin: Base URL of the origin server (must be provided).

Clear the Cache via CLI

java -jar target/SpringSecurity-0.0.1-SNAPSHOT.jar --clearcache
Clear the Cache via HTTP

curl -X DELETE http://localhost:9001/clear-cache

Usage Example:

Proxying a GET Request:

curl http://localhost:9001/products
First request: Forwards to origin, caches response, returns with X-CACHE: MISS.

Subsequent requests: Returns cached response with X-CACHE: HIT.

Proxying a POST/PUT/PATCH Request:
The proxy will forward the request and cache the response as well.

Configuration:
You can also set properties in application.properties:
proxy.origin=https://dummyjson.com
server.port=9001

