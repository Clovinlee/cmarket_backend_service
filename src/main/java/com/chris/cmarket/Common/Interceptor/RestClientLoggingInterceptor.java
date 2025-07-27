package com.chris.cmarket.Common.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestClientLoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RestClientLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        log.info("============= Rest Client Logging Interceptor =============");
        log.info("➡️ {} {}", request.getMethod(), request.getURI());
        log.info("➡️ Headers: {}", request.getHeaders());
        log.info("➡️ Body: {}", new String(body, StandardCharsets.UTF_8));
        log.info("=============                                 ============");

        ClientHttpResponse response = execution.execute(request, body);

        log.info("⬅️ Status: {}", response.getStatusCode());
        log.info("=============                                 ============");

        return response;
    }
}
