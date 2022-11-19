package com.vladnickgo.Project.controller.dto;

import java.util.Objects;

public class PasswordEncryptionDto {
    private final String encryptedPassword;
    private final String salt;

    public PasswordEncryptionDto(String encryptedPassword, String salt) {
        this.encryptedPassword = encryptedPassword;
        this.salt = salt;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordEncryptionDto that = (PasswordEncryptionDto) o;
        return Objects.equals(encryptedPassword, that.encryptedPassword) && Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encryptedPassword, salt);
    }
}
