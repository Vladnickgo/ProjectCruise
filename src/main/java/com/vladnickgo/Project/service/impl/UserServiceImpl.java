package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.UserDao;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.service.UserService;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.PasswordEncryptionService;
import com.vladnickgo.Project.validator.UserValidator;
import com.vladnickgo.Project.validator.ValidatorErrorMessage;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private final UserDao userRepository;
    private final Mapper<UserDto, User> mapper;
    private final UserValidator userValidator;
    private final PasswordEncryptionService passwordEncryptionService;

    public UserServiceImpl(UserDao userRepository, Mapper<UserDto, User> mapper, UserValidator userValidator, PasswordEncryptionService passwordEncryptionService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userValidator = userValidator;
        this.passwordEncryptionService = passwordEncryptionService;
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = getUser(email);
        return mapper.mapEntityToDto(user);
    }

    private User getUser(String email) {
        userValidator.validateEmail(email);
        return userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException(String.format(ValidatorErrorMessage.USER_WITH_EMAIL_NOT_FOUND, email)));
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
        if (!Objects.equals(password, confirmationPassword) || Objects.equals(password, "")) {
            LOGGER.info(ValidatorErrorMessage.CONFIRMATION_PASSWORD_ERROR_MESSAGE);
            throw new IllegalArgumentException(ValidatorErrorMessage.CONFIRMATION_PASSWORD_ERROR_MESSAGE);
        }
        User user = mapper.mapDtoToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public UserDto login(String email, String password) {
        User user = getUser(email);
        try {
            if (passwordEncryptionService.authenticate(password, user.getPassword(), user.getSalt())) {
                return mapper.mapEntityToDto(user);
            }
            throw new IllegalArgumentException(ValidatorErrorMessage.PASSWORD_ERROR_MESSAGE);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(ValidatorErrorMessage.PASSWORD_ERROR_MESSAGE);
        }
    }

}
