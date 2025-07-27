package com.chris.cmarket.Common.Util;

import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@UtilityClass
public class PkceGeneratorUtil {

    /**
     * 48 bytes	~64 characters
     * 64 bytes	~86 characters
     * 96 bytes	~128 characters (max for PKCE)
     */
    private static final int PKCE_BYTE_LENGTH = 48;

    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * @return code verifier to be saved
     */
    public static String generateCodeVerifier() {
        return generateCodeVerifier(PKCE_BYTE_LENGTH);
    }

    /**
     * @param byteLength length of random byte
     * @return code verifier to be saved
     */
    public static String generateCodeVerifier(int byteLength) {
        byte[] randomBytes = new byte[byteLength];
        new SecureRandom().nextBytes(randomBytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);
    }

    /**
     * @param codeVerifier code verifier (saved in client)
     * @return code challenge to be sent to the oauth server
     */
    public static String generateCodeChallenge(String codeVerifier) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = digest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));

            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate code_challenge", e);
        }
    }
}
