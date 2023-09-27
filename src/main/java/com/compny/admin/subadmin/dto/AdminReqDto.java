package com.compny.admin.subadmin.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdminReqDto {
    @NotNull(message = "fullName is not must be null")
    @NotBlank(message = "fullName is not must be blank")
    @NotEmpty(message = "fullName is not must be empty")
    private String fullName;
    @Email(message = "email is not valid")
    private String email;
    @NotNull(message = "password is not must be null")
    @NotBlank(message = "password is not must be blank")
    @NotEmpty(message = "password is not must be empty")
    private String password;
}
