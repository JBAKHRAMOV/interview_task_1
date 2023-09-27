package com.compny.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class ForgotPasswordDto {
    @Email(message = "email is not valid")
    private String email;
}
