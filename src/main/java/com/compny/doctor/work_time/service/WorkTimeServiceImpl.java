package com.compny.doctor.work_time.service;

import com.compny.doctor.entity.DoctorEntity;
import com.compny.doctor.work_time.dto.WorkTimeReqDto;
import com.compny.doctor.work_time.dto.WorkTimeResDto;
import com.compny.doctor.work_time.entity.WorkTimeEntity;
import com.compny.doctor.work_time.repository.WorkTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("work-time-service")
public class WorkTimeServiceImpl implements WorkTimeService {

    private final WorkTimeRepository workTimeRepository;
    @Override
    public void createWorkTime(List<WorkTimeReqDto> list, DoctorEntity doctorEntity) {
        list
                .stream()
                .map(dto -> convertToEntity(dto, doctorEntity.getId()))
                .forEach(workTimeRepository::save);
    }

    @Override
    public List<WorkTimeResDto> getAllWorkTimesByDoctorId(String doctorId) {
        return workTimeRepository.findAllByDoctorId(doctorId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public void updateWorkTime(List<WorkTimeReqDto> list, DoctorEntity doctorEntity) {

        workTimeRepository.deleteAll(workTimeRepository.findAllByDoctorId(doctorEntity.getId()));

        list
                .stream()
                .map(dto -> convertToEntity(dto, doctorEntity.getId()))
                .forEach(workTimeRepository::save);
    }

    @Override
    public WorkTimeResDto getWorkTimeByDoctorIdAndDay(String doctorId, Integer day) {
        return convertToDto(workTimeRepository.findByDoctorIdAndDateOfWeek(doctorId, day)
                .orElseThrow(() -> new RuntimeException("Work time not found")));
    }


    private WorkTimeEntity convertToEntity(WorkTimeReqDto dto, String doctorId){
        WorkTimeEntity entity = new WorkTimeEntity();
        entity.setDateOfWeek(dto.getDayOfWeek());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setDoctorId(doctorId);
        return entity;
    }

    private WorkTimeResDto convertToDto(WorkTimeEntity workTimeEntity) {
        return new WorkTimeResDto(
                workTimeEntity.getId(),
                workTimeEntity.getDateOfWeek(),
                workTimeEntity.getStartTime(),
                workTimeEntity.getEndTime(),
                workTimeEntity.getDoctorId()
        );

    }
}
