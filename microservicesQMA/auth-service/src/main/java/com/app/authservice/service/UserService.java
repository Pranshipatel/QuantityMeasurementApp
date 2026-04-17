package com.app.authservice.service;

import com.app.authservice.dto.LoginDto;
import com.app.authservice.dto.SignupDto;
import com.app.authservice.dto.UserDto;

public interface UserService {
    UserDto createUser(SignupDto signupDto);
    UserDto getUserById(Long id);
    String login(LoginDto loginDto);
}
