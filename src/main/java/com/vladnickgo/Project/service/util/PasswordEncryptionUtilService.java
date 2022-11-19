package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.PasswordEncryptionDto;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordEncryptionUtilService {

    private static final Logger LOGGER = Logger.getLogger(PasswordEncryptionUtilService.class);

    public static boolean authenticate(String attemptedPassword, String encryptedPasswordHex, String saltHex) {
        String encryptedAttemptedPasswordHex = getEncryptedPassword(attemptedPassword, saltHex);
        byte[] encryptedAttemptedPassword = hexToByteArray(encryptedAttemptedPasswordHex);
        byte[] encryptedPassword = hexToByteArray(encryptedPasswordHex);
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    public static String getEncryptedPassword(String password, String salt) {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;
        int iterations = 20000;

        byte[] saltByteArray = hexToByteArray(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltByteArray, iterations, derivedKeyLength);
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        byte[] encoded;
        try {
            encoded = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return getHexString(encoded);
    }

    public static String generateSalt() {
        SecureRandom random;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return getHexString(salt);
    }

    public static PasswordEncryptionDto generateEncryptedPassword(String password) {
        String salt;
        String encryptedPassword;
        salt = generateSalt();
        encryptedPassword = getEncryptedPassword(password, salt);
        return new PasswordEncryptionDto(encryptedPassword, salt);

    }

    private static String getHexString(byte[] array) {
        StringBuilder result = new StringBuilder();
        for (byte b : array) {
            String hex = String.format("%02x", b);
            result.append(hex);
        }
        return result.toString();
    }

    private static byte[] hexToByteArray(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            String substring = hexString.substring(i * 2, i * 2 + 2);
            int integer = Integer.parseInt(substring, 16);
            bytes[i] = (byte) integer;
        }
        return bytes;
    }
}
