package com.compny.client.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClientReqDto {
    @NotNull(message = "fullName is not must be null")
    @NotEmpty(message = "fullName is not must be empty")
    @NotNull(message = "fullName is not must be blank")
    private String fullName;
    @NotNull(message = "email is not must be null")
    @NotEmpty(message = "email is not must be empty")
    @NotNull(message = "email is not must be blank")
    private String phone;
    @NotNull(message = "email is not must be null")
    @NotBlank(message = "email is not must be empty")
    private String otherInfo;
}
