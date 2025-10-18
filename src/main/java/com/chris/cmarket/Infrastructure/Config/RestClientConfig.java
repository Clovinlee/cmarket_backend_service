package com.chris.cmarket.Infrastructure.Config;

import com.chris.cmarket.Auth.Client.GithubOAuthClient;
import com.chris.cmarket.Common.Interceptor.RestClientLoggingInterceptor;
import com.chris.cmarket.Order.Client.OrderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Bean
    public GithubOAuthClient githubOauthClient() {
        RestClient client = RestClient
                .builder()
                .requestInterceptor(new RestClientLoggingInterceptor())
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(client)
        ).build();

        return factory.createClient(GithubOAuthClient.class);
    }

    @Bean
    public OrderClient orderClient(@Value("${order-service.base-url}") String baseUrl) {
        RestClient client = RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor(new RestClientLoggingInterceptor())
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(client)
        ).build();

        return factory.createClient(OrderClient.class);
    }
}
