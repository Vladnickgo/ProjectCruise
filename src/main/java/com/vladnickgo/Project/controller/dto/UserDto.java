package com.vladnickgo.Project.controller.dto;

import com.vladnickgo.Project.dao.entity.Role;

public class UserDto {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String confirmationPassword;
    private final Role role;

    private UserDto(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        password = builder.password;
        confirmationPassword = builder.confirmationPassword;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String confirmationPassword;
        private Role role;
        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder confirmationPassword(String val) {
            confirmationPassword = val;
            return this;
        }

        public Builder role(Role val) {
            role = val;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }

    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public Role getRole() {
        return role;
    }
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmationPassword='" + confirmationPassword + '\'' +
                ", role=" + role +
                '}';
    }
}