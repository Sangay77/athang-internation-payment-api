package com.athang.international.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class RSAKeyProvider {

    private static final String PUBLIC_KEY_PATH = "src/main/resources/pki/athang_public.key";
    private static final String PRIVATE_KEY_PATH = "src/main/resources/pki/athang_private.key";

    public PublicKey getPublicKey() throws Exception {
        String base64 = loadPem(PUBLIC_KEY_PATH);
        byte[] keyBytes = Base64.getDecoder().decode(base64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    public PrivateKey getPrivateKey() throws Exception {
        String base64 = loadPem(PRIVATE_KEY_PATH);
        byte[] keyBytes = Base64.getDecoder().decode(base64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    public String getPublicKeyBase64Url() throws Exception {
        String base64 = loadPem(PUBLIC_KEY_PATH);
        byte[] decoded = Base64.getDecoder().decode(base64);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(decoded);
    }

    private String loadPem(String path) throws IOException {
        String pem = Files.readString(Paths.get(path));
        return pem.replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)-----", "")
                .replaceAll("\\s", "");
    }
}
