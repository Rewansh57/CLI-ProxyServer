package org.example.springsecuritylearning.springsecurity.Context.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.net.http.HttpHeaders;
@RequiredArgsConstructor
@Getter
@Setter

public class CachedResponse {
    private static byte [] body;
    private static HttpHeaders header;





}
