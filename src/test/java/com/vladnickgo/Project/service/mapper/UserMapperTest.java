package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.PasswordEncryptionDto;
import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.entity.Role;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.service.util.PasswordEncryptionUtilService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @InjectMocks
    private UserMapper mapper;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideUserForCheckMapDtoToEntityMethod")
    void checkMapDtoToEntityMethod(UserDto userDto, User expectedUser, String message) {

        try (MockedStatic<PasswordEncryptionUtilService> utilities = Mockito.mockStatic(PasswordEncryptionUtilService.class)) {

            utilities.when(() -> PasswordEncryptionUtilService.generateEncryptedPassword(userDto.getPassword()))
                    .thenReturn(new PasswordEncryptionDto("pass", "salt"));

            User user = mapper.mapDtoToEntity(userDto);
            assertEquals(expectedUser, user, message);
        }

    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideDataForCheckMapEntityToDtoMethod")
    void checkMapEntityToDtoMethod(User user, UserDto expectedUserDto, String message) {
            UserDto actualUserDto = mapper.mapEntityToDto(user);
            assertEquals(expectedUserDto, actualUserDto, message);
    }


    private static Stream<Arguments> provideUserForCheckMapDtoToEntityMethod() {
        return Stream.of(
                Arguments.of(UserDto.newBuilder()
                                .id(1)
                                .firstName("Bertrand")
                                .lastName("Mayer")
                                .email("mayer@mail.com")
                                .password("1234")
                                .confirmationPassword("1234")
                                .role(Role.USER)
                                .build(),
                        User.newBuilder()
                                .id(1)
                                .firstName("Bertrand")
                                .lastName("Mayer")
                                .email("mayer@mail.com")
                                .password("pass")
                                .salt("salt")
                                .role(Role.USER)
                                .build(),
                        "Check mapDtoToEntityMethod"),
                Arguments.of(null,
                        null,
                        "Check mapDtoToEntityMethod with null values"
                )
        );
    }

    private static Stream<Arguments> provideDataForCheckMapEntityToDtoMethod() {
        return Stream.of(
                Arguments.of(
                        User.newBuilder()
                                .id(1)
                                .firstName("Bertrand")
                                .lastName("Mayer")
                                .email("mayer@mail.com")
                                .password("pass")
                                .salt("salt")
                                .role(Role.USER)
                                .build(),
                        UserDto.newBuilder()
                                .id(1)
                                .firstName("Bertrand")
                                .lastName("Mayer")
                                .email("mayer@mail.com")
                                .password("pass")
                                .confirmationPassword("pass")
                                .role(Role.USER)
                                .build(),
                        "Check mapEntityToDtoMethod"),
                Arguments.of(
                        null,
                        null,
                        "Check mapDtoToEntityMethod with null values"
                )
        );
    }

}