package com.compny.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdminLoginDto {
    @NotEmpty(message = "keyWord is required")
    @NotNull(message = "keyWord is required")
    @NotBlank(message = "keyWord is required")
    private String keyWord;
    @NotNull(message = "password is required")
    @NotEmpty(message = "password is required")
    @NotBlank(message = "password is required")
    private String password;
}
