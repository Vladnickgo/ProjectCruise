package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.PasswordEncryptionDto;
import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.service.util.PasswordEncryptionUtilService;

public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public User mapDtoToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        PasswordEncryptionDto passwordEncryptionDto = PasswordEncryptionUtilService.generateEncryptedPassword(userDto.getPassword());
        return User.newBuilder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .salt(passwordEncryptionDto.getSalt())
                .password(passwordEncryptionDto.getEncryptedPassword())
                .role(userDto.getRole())
                .build();
    }

    @Override
    public UserDto mapEntityToDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.newBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .confirmationPassword(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }

}
