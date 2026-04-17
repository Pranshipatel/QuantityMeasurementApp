package com.app.authservice.service.impl;

import com.app.authservice.dto.LoginDto;
import com.app.authservice.dto.SignupDto;
import com.app.authservice.dto.UserDto;
import com.app.authservice.exception.UserNotFoundException;
import com.app.authservice.mapper.Mapper;
import com.app.authservice.model.User;
import com.app.authservice.repository.UserRepo;
import com.app.authservice.service.JWTService;
import com.app.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final Mapper<SignupDto, User> signupRequestMapper;
    private final Mapper<User, UserDto> userResponseMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public UserDto createUser(SignupDto signupDto) {
        User user = signupRequestMapper.mapTo(signupDto);
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        User savedUser = userRepo.save(user);
        return userResponseMapper.mapTo(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with user id " + id + " does not exist!"));
        return userResponseMapper.mapTo(user);
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        String token = jwtService.generateToken(authentication.getName());
        return token;
    }
}
