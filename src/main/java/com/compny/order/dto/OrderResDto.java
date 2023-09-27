package com.compny.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class OrderResDto {
    private Long id;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String clientId;
    private String doctorId;
}
