package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.service.util.PasswordEncryptionService;
import com.vladnickgo.Project.validator.ValidatorErrorMessage;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public User mapDtoToEntity(UserDto userDto) {
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        String salt;
        String encryptedPassword;
        try {
            salt = passwordEncryptionService.generateSalt();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(ValidatorErrorMessage.PASSWORD_ERROR_MESSAGE);
        }
        try {
            encryptedPassword = passwordEncryptionService.getEncryptedPassword(userDto.getPassword(), salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(ValidatorErrorMessage.PASSWORD_ERROR_MESSAGE);
        }
        return User.newBuilder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .salt(salt)
                .password(encryptedPassword)
                .role(userDto.getRole())
                .build();
    }

    @Override
    public UserDto mapEntityToDto(User user) {
        return UserDto.newBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }

}
