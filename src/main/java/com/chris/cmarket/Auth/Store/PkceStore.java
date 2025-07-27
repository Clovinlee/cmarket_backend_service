package com.chris.cmarket.Auth.Store;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Profile("dev")
@Component
public class PkceStore {

    private final Map<String, String> store = new ConcurrentHashMap<>();

    /**
     * @param key          oauth provider (e.g github, google, ..)
     * @param codeVerifier random string generated to be sent
     */
    public void save(String key, String codeVerifier) {
        store.put(key, codeVerifier);
    }

    public String get(String key) {
        return this.get(key, false);
    }

    public String get(String key, boolean persist) {
        if (persist) {
            return store.get(key);
        }

        return store.remove(key);
    }
}