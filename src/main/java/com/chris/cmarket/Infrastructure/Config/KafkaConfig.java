package com.chris.cmarket.Infrastructure.Config;

import com.chris.cmarket.Order.Event.PlaceOrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic placeOrderTopic() {
        return new NewTopic(PlaceOrderEvent.TOPIC_NAME, 1, (short) 1);
    }
}
