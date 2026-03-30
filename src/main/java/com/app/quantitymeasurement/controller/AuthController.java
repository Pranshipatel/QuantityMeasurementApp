package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.LoginDto;
import com.app.quantitymeasurement.dto.UserDto;
import com.app.quantitymeasurement.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.dto.SignupDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
	private final UserService userService;
	
	@GetMapping("/user")
	public ResponseEntity<?> user(@AuthenticationPrincipal OAuth2User principal) {
	    if (principal == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body("User not authenticated");
	    }
	    return ResponseEntity.ok(principal.getAttributes());
	}

	@PostMapping("/signup")
	public ResponseEntity<UserDto> singup(@Valid @RequestBody SignupDto sinupDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(sinupDto));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {
		String token = userService.login(loginDto);
		return ResponseEntity.accepted().body(token);
	}
}