package com.compny.doctor.dto;

import com.compny.doctor.work_time.dto.WorkTimeResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResDto {
    private String id;
    private String name;
    private String surname;
    private String experience;
    private Double price;
    private String specialtyId;
    private String workPlaceId;
}
