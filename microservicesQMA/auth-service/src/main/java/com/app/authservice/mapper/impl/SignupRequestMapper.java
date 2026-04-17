package com.app.authservice.mapper.impl;

import com.app.authservice.dto.SignupDto;
import com.app.authservice.mapper.Mapper;
import com.app.authservice.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignupRequestMapper implements Mapper<SignupDto, User> {
    private final ModelMapper modelMapper;

    @Override
    public User mapTo(SignupDto signupDto) {
        return modelMapper.map(signupDto, User.class);
    }

    @Override
    public SignupDto mapFrom(User user) {
        return modelMapper.map(user, SignupDto.class);
    }
}
