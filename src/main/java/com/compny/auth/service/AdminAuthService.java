package com.compny.auth.service;


import com.compny.auth.dto.AdminLoginDto;
import com.compny.auth.dto.ForgotPasswordDto;
import com.compny.auth.dto.TokenDto;
import com.compny.component.ResDTO;

public interface AdminAuthService {

    TokenDto login(AdminLoginDto dto);

    ResDTO forgotPassword(ForgotPasswordDto dto);
    ResDTO confirmPasswd(String token);

}
