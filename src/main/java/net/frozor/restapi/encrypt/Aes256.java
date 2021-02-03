package net.frozor.restapi.encrypt;

import org.springframework.security.crypto.encrypt.AesBytesEncryptor;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Aes256 {

    private static final AesBytesEncryptor aesBytesEncryptor = new AesBytesEncryptor("password","55");

    public static String encrypt(String input) {
        byte[] encryptionBytes = aesBytesEncryptor.encrypt(input.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(encryptionBytes);
    }

    public static String decrypt(String encrypted) {
        return new String(aesBytesEncryptor.decrypt(Base64.getDecoder().decode(encrypted)));
    }
}