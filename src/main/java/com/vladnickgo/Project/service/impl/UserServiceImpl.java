package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.UserDao;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.service.UserService;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.PasswordEncryptionUtilService;
import com.vladnickgo.Project.validator.UserValidator;
import com.vladnickgo.Project.validator.ValidatorErrorMessage;
import org.apache.log4j.Logger;

import java.util.Objects;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private final UserDao userRepository;
    private final Mapper<UserDto, User> mapper;
    private final UserValidator userValidator;

    public UserServiceImpl(UserDao userRepository, Mapper<UserDto, User> mapper, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userValidator = userValidator;
    }

    @Override
    public UserDto findByEmail(String email) {
        userValidator.validateEmail(email);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException(String.format(ValidatorErrorMessage.USER_WITH_EMAIL_NOT_FOUND, email)));
        return mapper.mapEntityToDto(user);
    }

    @Override
    public void save(UserDto userDto) {
        userValidator.validate(userDto);
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            String message = String.format(ValidatorErrorMessage.USER_ALREADY_EXIST_ERROR_MESSAGE, userDto.getEmail());
            LOGGER.info(message);
            throw new EntityAlreadyExistException(message);
        }
        String password = userDto.getPassword();
        String confirmationPassword = userDto.getConfirmationPassword();
        if (!Objects.equals(password, confirmationPassword)) {
            LOGGER.info(ValidatorErrorMessage.CONFIRMATION_PASSWORD_ERROR_MESSAGE);
            throw new IllegalArgumentException(ValidatorErrorMessage.CONFIRMATION_PASSWORD_ERROR_MESSAGE);
        }
        User user = mapper.mapDtoToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public UserDto login(String email, String password) {
        userValidator.validateEmail(email);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException(String.format(ValidatorErrorMessage.USER_WITH_EMAIL_NOT_FOUND, email)));
        if (PasswordEncryptionUtilService.authenticate(password, user.getPassword(), user.getSalt())) {
            return mapper.mapEntityToDto(user);
        }
        LOGGER.info(ValidatorErrorMessage.PASSWORD_ERROR_MESSAGE);
        throw new IllegalArgumentException(ValidatorErrorMessage.PASSWORD_ERROR_MESSAGE);
    }

}
