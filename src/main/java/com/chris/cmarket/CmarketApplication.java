package com.chris.cmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.chris.cmarket.Configs.Properties.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class CmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmarketApplication.class, args);
	}

}
