package com.chris.cmarket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import com.chris.cmarket.Configs.Properties.JwtProperties;

@SpringBootTest
@EnableConfigurationProperties(JwtProperties.class)
class CmarketApplicationTests {

	@Test
	void contextLoads() {
	}

}
