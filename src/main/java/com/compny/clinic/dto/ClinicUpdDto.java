package com.compny.clinic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClinicUpdDto {
    @NotEmpty(message = "id is not must be empty")
    @NotNull(message = "id is not must be null")
    @NotBlank(message = "id is not must be blank")
    private String id;
    @NotBlank(message = "name is not must be blank")
    @NotNull(message = "name is not must be null")
    @NotEmpty(message = "name is not must be empty")
    private String name;
    @NotBlank(message = "ipAddress is not must be blank")
    @NotNull(message = "ipAddress is not must be null")
    @NotEmpty(message = "ipAddress is not must be empty")
    private String ipAddress;
}
