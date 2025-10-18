package com.chris.cmarket.Order.Client;

import com.chris.cmarket.Common.Response.APIResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface OrderClient {
    @PostExchange(value = "/order/uuid", accept = "application/json")
    APIResponse<String> getOrderUuid(@RequestHeader("Authorization") String bearerToken);
}
