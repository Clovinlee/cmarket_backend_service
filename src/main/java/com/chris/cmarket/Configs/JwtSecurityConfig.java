package com.chris.cmarket.Configs;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.encrypt.KeyStoreKeyFactory;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.chris.cmarket.Configs.Properties.JwtProperties;
import com.chris.cmarket.Constants.CmarketLoadOrderConstant;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@Order(CmarketLoadOrderConstant.SECURITY_ORDER_AUTHENTICATED)
public class JwtSecurityConfig {

    private final JwtProperties jwtProperties;
    private final ResourceLoader resourceLoader;

    public JwtSecurityConfig(JwtProperties jwtProperties, ResourceLoader resourceLoader) {
        this.jwtProperties = jwtProperties;
        this.resourceLoader = resourceLoader;
    }

    @Bean
    KeyPair keyPair() {
        Resource keyStore = resourceLoader.getResource(jwtProperties.keyStore());

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                keyStore,
                jwtProperties.storePassword().toCharArray(),
                "PKCS12");

        return keyStoreKeyFactory.getKeyPair(jwtProperties.keyAlias());
    }

    @Bean
    JwtEncoder jwtEncoder(KeyPair keyPair) {
        RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .build();

        var jwkSet = new JWKSet(rsaKey);
        var jwkSource = new ImmutableJWKSet<>(jwkSet);

        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    JwtDecoder jwtDecoder(KeyPair keyPair) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }
}
