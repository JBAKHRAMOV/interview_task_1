package com.compny.clinic.dto;

import com.compny.base.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClinicChangeStatusDto {
    @NotEmpty(message = "id is not must be empty")
    @NotNull(message = "id is not must be null")
    @NotBlank(message = "id is not must be blank")
    private String id;
    @NotNull(message = "status is not must be null")
    @NotBlank(message = "status is not must be blank")
    @NotEmpty(message = "status is not must be empty")
    private Status status;
}
