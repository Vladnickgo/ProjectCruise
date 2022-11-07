package com.vladnickgo.Project.service.mapper;

import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.entity.Role;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.service.util.PasswordEncryptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @Mock
    private PasswordEncryptionService passwordEncryptionService;
    private final Mapper<UserDto, User> mapper = new UserMapper();

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideUserForCheckMapDtoToEntityMethod")
    void checkMapDtoToEntityMethod(UserDto userDto, User expectedUser, String message) {
        User user = mapper.mapDtoToEntity(userDto);
        User actualUser = User.newBuilder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        assertEquals(expectedUser, actualUser, message);
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
                                .role(Role.USER)
                                .build(),
                        "Check mapDtoToEntityMethod"),
                Arguments.of(UserDto.newBuilder()
                                .id(2)
                                .firstName("Bob")
                                .lastName("Martyn")
                                .email("martyn@mail.com")
                                .password("1234")
                                .confirmationPassword("1234")
                                .role(Role.ADMIN)
                                .build(),
                        User.newBuilder()
                                .id(2)
                                .firstName("Bob")
                                .lastName("Martyn")
                                .email("martyn@mail.com")
                                .role(Role.ADMIN)
                                .build(),
                        "Check mapDtoToEntityMethod"
                )
        );
    }
}