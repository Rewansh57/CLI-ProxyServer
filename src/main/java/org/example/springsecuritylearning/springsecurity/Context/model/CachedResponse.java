package org.example.springsecuritylearning.springsecurity.Context.model;

import org.springframework.http.HttpHeaders;


public class CachedResponse {
    private static byte [] body;
    private static HttpHeaders header;

    public CachedResponse(byte [] body, HttpHeaders header) {
        this.body = body;
        this.header = header;

    }

    public static byte[] getBody() {
        return body;
    }

    public static void setBody(byte[] body) {
        CachedResponse.body = body;
    }

    public static HttpHeaders getHeader() {
        return header;
    }

    public static void setHeader(HttpHeaders header) {
        CachedResponse.header = header;
    }
}
