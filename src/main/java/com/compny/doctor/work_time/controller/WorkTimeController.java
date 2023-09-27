package com.compny.doctor.work_time.controller;

import com.compny.doctor.work_time.dto.WorkTimeResDto;
import com.compny.doctor.work_time.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/work-times")
public class WorkTimeController {

    @Qualifier("work-time-service")
    private final WorkTimeService workTimeService;

    @GetMapping("/all/{doctorId}")
    public ResponseEntity<List<WorkTimeResDto>> getAllWorkTimesByDoctorId(@PathVariable(value = "doctorId") String doctorId) {
        return ResponseEntity.ok(workTimeService.getAllWorkTimesByDoctorId(doctorId));
    }

}
