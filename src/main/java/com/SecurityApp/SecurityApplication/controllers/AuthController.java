package com.SecurityApp.SecurityApplication.controllers;

import com.SecurityApp.SecurityApplication.dto.LoginDTO;
import com.SecurityApp.SecurityApplication.dto.SignupDTO;
import com.SecurityApp.SecurityApplication.dto.UserDTO;
import com.SecurityApp.SecurityApplication.services.AuthService;
import com.SecurityApp.SecurityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignupDTO signupDTO){
        UserDTO userDTO = userService.signUp(signupDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,
                                        HttpServletResponse response){
        String token = authService.login(loginDTO);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

}
