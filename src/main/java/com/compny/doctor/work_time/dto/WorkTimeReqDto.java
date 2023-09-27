package com.compny.doctor.work_time.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
public class WorkTimeReqDto {
    @NotNull(message = "startTime must not be null")
    private LocalTime startTime;
    @NotNull(message = "endTime must not be null")
    private LocalTime endTime;
    @NotNull(message = "dayOfWeek must not be null")
    private Integer dayOfWeek;
}
