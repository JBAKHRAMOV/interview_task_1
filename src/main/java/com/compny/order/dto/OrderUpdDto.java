package com.compny.order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class OrderUpdDto {
    @NotNull(message = "id is not must be null")
    @NotBlank(message = "id is not must be blank")
    @NotEmpty(message = "id is not must be empty")
    private Long id;
    @NotNull(message = "description is not must be null")
    @NotBlank(message = "description is not must be blank")
    private String description;
    @NotNull(message = "startTime is not must be null")
    private LocalTime startTime;
    @NotNull(message = "endTime is not must be null")
    private LocalTime endTime;
    @NotNull(message = "date is not must be null")
    private LocalDate date;
    @NotNull(message = "clientId is not must be null")
    @NotEmpty(message = "clientId is not must be empty")
    @NotBlank(message = "clientId is not must be blank")
    private String doctorId;
}
