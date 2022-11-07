package com.vladnickgo.Project.service.impl;

import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.UserDao;
import com.vladnickgo.Project.dao.entity.Role;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.service.impl.exception.EntityAlreadyExistException;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.util.PasswordEncryptionService;
import com.vladnickgo.Project.validator.UserValidator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private Mapper<UserDto, User> mapper;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserDto userDto;

    @Mock
    private PasswordEncryptionService passwordEncryptionService;

    @InjectMocks
    private UserServiceImpl userService;

    @ParameterizedTest(name = "[{index}]{1}")
    @MethodSource("provideEmailsThatNotRegistered")
    public void findByEmail(String email, String message) {
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.empty());
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> userService.findByEmail(email));
        String actualExceptionMessage = illegalArgumentException.getMessage();
        String expectedExceptionMessage = String.format("User with email <strong>%s</strong> not found", email);
        assertEquals(expectedExceptionMessage, actualExceptionMessage, message);
    }

    @ParameterizedTest(name = "[{index}]{1}")
    @MethodSource("provideUserDtoForCheckingSaveMethod")
    public void checkSave(UserDto userDto, String expectedExceptionMessage) {
        Optional<User> user = Optional.of(User.newBuilder().build());
        Mockito.when(userDao.findByEmail(userDto.getEmail())).thenReturn(user);
        EntityAlreadyExistException entityAlreadyExistException = assertThrows(EntityAlreadyExistException.class, () -> userService.save(userDto));
        String actualExceptionMessage = entityAlreadyExistException.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @ParameterizedTest(name = "[{index}]{4}")
    @MethodSource("provideNotValidEmail")
    public void loginTestForNotValidPassword(String email, String password, User user, UserDto userDto, String expectedExceptionMessage, String message) throws InvalidKeySpecException {
        Mockito.doNothing().when(userValidator).validateEmail(email);
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.of(user));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> userService.login(email, password));
        String actualExceptionMessage = illegalArgumentException.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideEmailForFindByEmail")
    public void checkFindByEmailForExistUser(String email, User user, UserDto expectedUserDto, String message) {
        Mockito.doNothing().when(userValidator).validateEmail(email);
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.ofNullable(user));
        Mockito.when(mapper.mapEntityToDto(user)).thenReturn(expectedUserDto);

        UserDto actualUserDto = userService.findByEmail(email);
        assertEquals(expectedUserDto, actualUserDto);
    }

    @ParameterizedTest(name = "[{index}]{4}")
    @MethodSource("provideEmailAndPasswordForLogin")
    public void checkLoginForExistingUser(String email, String password, User user, UserDto userDto, String message) {
        Mockito.doNothing().when(userValidator).validateEmail(email);
        Mockito.when(userDao.findByEmail(email)).thenReturn(Optional.ofNullable(user));
        Mockito.when(mapper.mapEntityToDto(user)).thenReturn(userDto);
        UserDto actualUserDto = userService.login(email, password);
        assertEquals(userDto, actualUserDto, message);
    }

    private static Stream<Arguments> provideEmailsThatNotRegistered() {
        return Stream.of(Arguments.of("123@mail.com",
                        "Email not registered"),
                Arguments.of("test@test.com",
                        "Email not registered"),
                Arguments.of("ivan@mail.com",
                        "Email not registered")
        );
    }

    private static Stream<Arguments> provideUserDtoForCheckingSaveMethod() {
        return Stream.of(Arguments.of(UserDto.newBuilder()
                                .id(1)
                                .firstName("John")
                                .lastName("Doe")
                                .email("test@gmail.com")
                                .password("somePassword")
                                .role(Role.USER)
                                .confirmationPassword("Password")
                                .build(),
                        "User with email <strong>test@gmail.com</strong> is already registered"
                ),
                Arguments.of(UserDto.newBuilder()
                                .id(2)
                                .firstName("Pol")
                                .lastName("Grant")
                                .email("post@gmail.com")
                                .password("somePassword")
                                .role(Role.USER)
                                .confirmationPassword("")
                                .build(),
                        "User with email <strong>post@gmail.com</strong> is already registered"
                ),
                Arguments.of(UserDto.newBuilder()
                                .id(112)
                                .firstName("Deny")
                                .lastName("Dax")
                                .email("dax@gmail.com")
                                .password("")
                                .role(Role.USER)
                                .confirmationPassword("")
                                .build(),
                        "User with email <strong>dax@gmail.com</strong> is already registered"
                ));
    }

    private static Stream<Arguments> provideNotValidEmail() {
        return Stream.of(
                Arguments.of(
                        "van@test.com",
                        "12345",
                        User.newBuilder()
                                .email("ivan@mail.com")
                                .password("fd06dd38d1fd964b21fbfa73c207cda337c45c3a")
                                .salt("7f1195dbf9222e9a")
                                .build(),
                        UserDto.newBuilder()
                                .email("ivan@mail.com")
                                .password("123")
                                .build(),
                        "Password is not valid",
                        "Not valid password"),
                Arguments.of(
                        "asdvan@test.com",
                        "123",
                        User.newBuilder()
                                .email("ivan@mail.com")
                                .password("12345")
                                .salt("7f1195dbf9222e9a")
                                .build(),
                        UserDto.newBuilder()
                                .email("ivan@mail.com")
                                .password("12345")
                                .build(),
                        "Password is not valid",
                        "Not valid password"),
                Arguments.of(
                        "test_email@test.com",
                        "123",
                        User.newBuilder()
                                .email("ivan@mail.com")
                                .password("")
                                .salt("7f1195dbf9222e9a")
                                .build(),
                        UserDto.newBuilder()
                                .email("ivan@mail.com")
                                .password("12345")
                                .build(),
                        "Password is not valid",
                        "Not valid password")
        );
    }

    private static Stream<Arguments> provideEmailForFindByEmail() {
        return Stream.of(
                Arguments.of(
                        "bob_martin@test.com",
                        User.newBuilder()
                                .id(5)
                                .firstName("Bob")
                                .lastName("Martin")
                                .email("bob_martin@test.com")
                                .password("martin123")
                                .role(Role.ADMIN)
                                .build(),
                        UserDto.newBuilder()
                                .id(5)
                                .firstName("Bob")
                                .lastName("Martin")
                                .email("bob_martin@test.com")
                                .password("martin123")
                                .role(Role.ADMIN)
                                .build(),
                        "User found"
                ),
                Arguments.of("bertrand_mayer@test.com",
                        User.newBuilder()
                                .id(5)
                                .firstName("Bertrand")
                                .lastName("Mayer")
                                .email("bertrand_mayer@test.com")
                                .password("mayer23")
                                .role(Role.ADMIN)
                                .build(),
                        UserDto.newBuilder()
                                .id(5)
                                .firstName("Bertrand")
                                .lastName("Mayer")
                                .email("bertrand_mayer@test.com")
                                .password("mayer23")
                                .role(Role.ADMIN)
                                .build(),
                        "User found"));
    }

    private static Stream<Arguments> provideEmailAndPasswordForLogin() {
        return Stream.of(
                Arguments.of("bob_martin@test.com",
                        "martin123",
                        User.newBuilder()
                                .id(5)
                                .firstName("Bob")
                                .lastName("Martin")
                                .email("bob_martin@test.com")
                                .password("57023bb780e4e312a44c02dcdbbd1fd441e71736")
                                .salt("813722871d5975e0")
                                .role(Role.ADMIN)
                                .build(),
                        UserDto.newBuilder()
                                .id(5)
                                .firstName("Bob")
                                .lastName("Martin")
                                .email("bob_martin@test.com")
                                .password("martin123")
                                .role(Role.ADMIN)
                                .build(),
                        "User Bob Martin is login"),

                Arguments.of(
                        "bertrand_mayer@test.com",
                        "mayer23",
                        User.newBuilder()
                                .id(6)
                                .firstName("Bertrand")
                                .lastName("Mayer")
                                .email("bertrand_mayer@test.com")
                                .password("b56f081bf515e3222e26e12f14a6171fed53486a")
                                .salt("958788940305a349")
                                .role(Role.ADMIN)
                                .build(),
                        UserDto.newBuilder()
                                .id(6)
                                .firstName("Bertrand")
                                .lastName("Mayer")
                                .email("bertrand_mayer@test.com")
                                .password("mayer23")
                                .role(Role.ADMIN)
                                .build(),
                        "User Bertrand Mayer is login")
        );
    }





}