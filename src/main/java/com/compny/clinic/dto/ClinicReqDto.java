package com.compny.clinic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClinicReqDto {
    @NotBlank(message = "name is not must be blank")
    @NotNull(message = "name is not must be null")
    @NotEmpty(message = "name is not must be empty")
    private String name;
    @NotBlank(message = "ipAddress is not must be blank")
    @NotNull(message = "ipAddress is not must be null")
    @NotEmpty(message = "ipAddress is not must be empty")
    private String ipAddress;
    @NotBlank(message = "fullName is not must be blank")
    @NotNull(message = "fullName is not must be null")
    @NotEmpty(message = "fullName is not must be empty")
    private String fullName;
    @Email
    private String email;
}
