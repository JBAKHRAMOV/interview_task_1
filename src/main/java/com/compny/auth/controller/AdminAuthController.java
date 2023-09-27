package com.compny.auth.controller;


import com.compny.auth.dto.AdminLoginDto;
import com.compny.auth.dto.ForgotPasswordDto;
import com.compny.auth.dto.TokenDto;
import com.compny.auth.service.AdminAuthService;
import com.compny.component.ResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AdminAuthController {

    @Qualifier(value = "admin-auth-service")
    private final AdminAuthService service;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid AdminLoginDto dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResDTO> forgotPassword(@RequestBody @Valid ForgotPasswordDto dto) {
        return ResponseEntity.ok(service.forgotPassword(dto));
    }

    @PostMapping("/confirm-password/{token}")
    public ResponseEntity<ResDTO> confirmPassword(@PathVariable String token) {
        return ResponseEntity.ok(service.confirmPasswd(token));
    }

}
