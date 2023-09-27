package com.compny.clinic.dto;

import com.compny.base.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClinicResDto {
    private String id;
    private String name;
    private String ipAddress;
    private Status status;
}
