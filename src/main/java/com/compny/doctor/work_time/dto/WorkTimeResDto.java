package com.compny.doctor.work_time.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkTimeResDto {
    private String id;
    private Integer day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String doctorId;
}
