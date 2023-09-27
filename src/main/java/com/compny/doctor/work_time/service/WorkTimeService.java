package com.compny.doctor.work_time.service;

import com.compny.doctor.entity.DoctorEntity;
import com.compny.doctor.work_time.dto.WorkTimeReqDto;
import com.compny.doctor.work_time.dto.WorkTimeResDto;

import java.time.LocalDate;
import java.util.List;

public interface WorkTimeService {

    void createWorkTime(List<WorkTimeReqDto> list, DoctorEntity doctorEntity);

    List<WorkTimeResDto> getAllWorkTimesByDoctorId(String doctorId);

    void updateWorkTime(List<WorkTimeReqDto> dto, DoctorEntity doctorEntity);

    WorkTimeResDto getWorkTimeByDoctorIdAndDay(String doctorId, Integer day);
}
