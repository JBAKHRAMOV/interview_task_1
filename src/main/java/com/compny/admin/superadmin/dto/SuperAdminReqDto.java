package com.compny.admin.superadmin.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SuperAdminReqDto {
    @NotBlank(message = "fullName is not must be blank")
    @NotNull(message = "fullName is not must be null")
    @NotEmpty(message = "fullName is not must be empty")
    private String fullName;
    @Email
    private String email;
    @NotEmpty(message = "password is not must be empty")
    @NotNull(message = "password is not must be null")
    @NotBlank(message = "password is not must be blank")
    private String password;
}
