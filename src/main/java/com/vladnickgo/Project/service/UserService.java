package com.vladnickgo.Project.service;


import com.vladnickgo.Project.controller.dto.UserDto;

public interface UserService {

    UserDto findByEmail(String email);

    void save(UserDto userDto);

    UserDto login(String email, String password);
}
