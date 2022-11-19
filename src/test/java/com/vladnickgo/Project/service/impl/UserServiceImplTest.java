package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.UserDao;
import com.vladnickgo.Project.dao.entity.Role;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.service.mapper.UserMapper;
import com.vladnickgo.Project.service.util.PasswordEncryptionUtilService;
import com.vladnickgo.Project.validator.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private static final String EXIST_EMAIL = "exist_email@gmail.com";
    private static final String NOT_EXIST_EMAIL = "not_exist_email@gmail.com";
    public static final String USER_EMAIL = "test@gmail.com";
    private static final User TEST_USER_ENTITY = User.newBuilder()
            .id(1)
            .email(USER_EMAIL)
            .firstName("Bob")
            .lastName("Spounch")
            .password("qwerty1234")
            .salt("413d85ce85cb1816")
            .role(Role.USER)
            .build();

    private static final UserDto TEST_USER_DTO = UserDto.newBuilder()
            .id(1)
            .email(USER_EMAIL)
            .firstName("Bob")
            .lastName("Spounch")
            .password("qwerty1234")
            .confirmationPassword("qwerty1234")
            .role(Role.USER)
            .build();

    @Mock
    private UserDao userDao;

    @Spy
    private UserMapper mapper;

    @Spy
    private UserValidator userValidator;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void findByEmailSuccessful() {
        doNothing().when(userValidator).validateEmail(anyString());
        when(userDao.findByEmail(EXIST_EMAIL)).thenReturn(Optional.of(TEST_USER_ENTITY));

        UserDto actual = userService.findByEmail(EXIST_EMAIL);

        assertEquals(TEST_USER_DTO, actual);

    }

    @Test
    public void findByEmailNotFoundUser() {

        when(userDao.findByEmail(NOT_EXIST_EMAIL)).thenReturn(Optional.empty());
        String expectedExceptionMessage = "User with email <strong>" + NOT_EXIST_EMAIL +
                "</strong> not found";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.findByEmail(NOT_EXIST_EMAIL));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void saveUserIsNull() {
        String expectedExceptionMessage = "User is null";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.save(null));
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    public void saveSuccessful() {
        when(mapper.mapDtoToEntity(any(UserDto.class))).thenReturn(TEST_USER_ENTITY);
        when(userDao.findByEmail(USER_EMAIL)).thenReturn(Optional.empty());

        userService.save(TEST_USER_DTO);

        verify(userDao).save(TEST_USER_ENTITY);
    }

    @Test
    public void saveWhenPasswordsMissMatch() {

        UserDto TEST_USER_DTO_WITH_DIFFERENT_PASSWORDS = UserDto.newBuilder()
                .id(1)
                .email(USER_EMAIL)
                .firstName("Bob")
                .lastName("Spounch")
                .password("qwerty1234")
                .confirmationPassword("zxccvb1234")
                .role(Role.USER)
                .build();

        when(userDao.findByEmail(USER_EMAIL)).thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.save(TEST_USER_DTO_WITH_DIFFERENT_PASSWORDS));

        String EXPECTED_EXCEPTION_MESSAGE = "ConfirmationPassword is not valid";

        assertEquals(EXPECTED_EXCEPTION_MESSAGE, exception.getMessage());

    }

    @Test
    public void loginSuccessful() {

        try (MockedStatic<PasswordEncryptionUtilService> utilities = Mockito.mockStatic(PasswordEncryptionUtilService.class)) {
            when(userDao.findByEmail(USER_EMAIL)).thenReturn(Optional.of(TEST_USER_ENTITY));

            utilities.when(() -> PasswordEncryptionUtilService.authenticate(any(), anyString(), anyString())).thenReturn(true);
            UserDto login = userService.login(USER_EMAIL, TEST_USER_DTO.getPassword());

            assertNotNull(login);

        }
    }

    @Test
    public void loginFailed() {

        try (MockedStatic<PasswordEncryptionUtilService> utilities = Mockito.mockStatic(PasswordEncryptionUtilService.class)) {
            when(userDao.findByEmail(USER_EMAIL)).thenReturn(Optional.of(TEST_USER_ENTITY));
            utilities.when(() -> PasswordEncryptionUtilService.authenticate(any(), anyString(), anyString())).thenReturn(false);

            IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                    () -> userService.login(USER_EMAIL, TEST_USER_DTO.getPassword()));

            String EXPECTED_EXCEPTION_MESSAGE = "Password is not valid";
            assertEquals(EXPECTED_EXCEPTION_MESSAGE, exception.getMessage());

        }
    }

}