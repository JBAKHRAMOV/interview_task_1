package com.compny.doctor.dto;

import com.compny.doctor.work_time.dto.WorkTimeReqDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class DoctorReqDto {
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    @NotEmpty(message = "name must not be empty")
    private String name;
    @NotNull(message = "surname must not be null")
    @NotBlank(message = "surname must not be blank")
    @NotEmpty(message = "surname must not be empty")
    private String surname;
    @NotNull(message = "experience must not be null")
    @NotBlank(message = "experience must not be blank")
    private String experience;
    @NotNull(message = "price must not be null")
    private Double price;
    @NotNull(message = "specialtyId must not be null")
    @NotBlank(message = "specialtyId must not be blank")
    @NotEmpty(message = "specialtyId must not be empty")
    private String specialtyId;
    @NotNull(message = "workPlaceId must not be null")
    @NotBlank(message = "workPlaceId must not be blank")
    @NotEmpty(message = "workPlaceId must not be empty")
    private String workPlaceId;
    @Size.List(
            {
                    @Size(min = 1, message = "timeList must not be empty"),
                    @Size(max = 7, message = "timeList must not be more than 7")
            }
    )
    private List<WorkTimeReqDto> timeList;
}
