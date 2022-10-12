package com.vladnickgo.Project.service.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordEncryptionService {
    public final boolean authenticate(String attemptedPassword, String encryptedPasswordHex, String saltHex) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String encryptedAttemptedPasswordHex = getEncryptedPassword(attemptedPassword, saltHex);
        byte[] encryptedAttemptedPassword = hexToByteArray(encryptedAttemptedPasswordHex);
        byte[] encryptedPassword = hexToByteArray(encryptedPasswordHex);
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }


    public final String getEncryptedPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;
        int iterations = 20000;

        byte[] saltByteArray = hexToByteArray(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltByteArray, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
        byte[] encoded = f.generateSecret(spec).getEncoded();
        return getHexString(encoded);
    }

    public final String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return getHexString(salt);
    }

    private String getHexString(byte[] array) {
        StringBuilder result = new StringBuilder();
        for (byte b : array) {
            String hex = String.format("%02x", b);
            result.append(hex);
        }
        return result.toString();
    }

    private byte[] hexToByteArray(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            String substring = hexString.substring(i * 2, i * 2 + 2);
            int integer = Integer.parseInt(substring, 16);
            bytes[i] = (byte) integer;
        }
        return bytes;
    }
}
