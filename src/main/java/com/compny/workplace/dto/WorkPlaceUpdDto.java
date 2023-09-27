package com.compny.workplace.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WorkPlaceUpdDto {
    @NotNull(message = "id is not must be null")
    @NotEmpty(message = "id is not must be empty")
    @NotBlank(message = "id is not must be blank")
    private String id;
    @NotNull(message = "name is not must be null")
    @NotBlank(message = "name is not must be blank")
    @NotEmpty(message = "name is not must be empty")
    private String name;
    @NotNull(message = "address is not must be null")
    @NotBlank(message = "address is not must be blank")
    @NotEmpty(message = "address is not must be empty")
    private String address;
    @NotNull(message = "latitude is not must be null")
    private Double latitude;
    @NotNull(message = "longitude is not must be null")
    private Double longitude;
}
