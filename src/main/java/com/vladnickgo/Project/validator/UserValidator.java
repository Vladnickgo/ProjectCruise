package com.vladnickgo.Project.validator;

import com.vladnickgo.Project.controller.dto.UserDto;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.vladnickgo.Project.validator.ValidatorErrorMessage.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class UserValidator implements Validator<UserDto> {

    @Override
    public void validate(UserDto entity) {
        if (entity == null) {
            throw new IllegalArgumentException(USER_IS_NULL_MESSAGE);
        }
        validateByParam(UserDto::getFirstName, Patterns.FIRST_NAME_PATTERN, FIRST_NAME_ERROR_MESSAGE, entity);
        validateByParam(UserDto::getLastName, Patterns.LAST_NAME_PATTERN, LAST_NAME_ERROR_MESSAGE, entity);
        validateByParam(UserDto::getEmail, Patterns.EMAIL_PATTERN, EMAIL_ERROR_MESSAGE, entity);
        validateByParam(UserDto::getPassword, Patterns.PASSWORD_PATTERN, PASSWORD_ERROR_MESSAGE, entity);
        validateByParam(UserDto::getConfirmationPassword, Patterns.PASSWORD_PATTERN, CONFIRMATION_PASSWORD_ERROR_MESSAGE, entity);
    }

    public void validateEmail(String email) {
        if (isBlank(email) || !email.matches(Patterns.REGEX_FOR_EMAIL)) {
            throw new IllegalArgumentException(EMAIL_ERROR_MESSAGE);
        }
    }

    private void validateByParam(Function<UserDto, String> param, Pattern pattern, String errorMessage, UserDto user) {
        Optional.ofNullable(param.apply(user))
                .filter(x -> pattern.matcher(x).matches())
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}