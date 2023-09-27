package com.compny.speciality.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SpecialtyReqDto {
    @NotNull(message = "id is not must be null")
    @NotEmpty(message = "id is not must be empty")
    @NotBlank(message = "id is not must be blank")
    private String id;
    @NotNull(message = "name is not must be null")
    @NotEmpty(message = "name is not must be empty")
    @NotBlank(message = "name is not must be blank")
    private String name;
}
