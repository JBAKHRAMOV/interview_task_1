package com.compny.doctor.service;

import com.compny.component.ResDTO;
import com.compny.doctor.dto.DoctorReqDto;
import com.compny.doctor.dto.DoctorResDto;
import com.compny.doctor.entity.DoctorEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DoctorService {

    ResDTO createDoctor(DoctorReqDto dto, MultipartFile file);

    DoctorResDto getDoctorById(String id);

    List<DoctorResDto> getAllDoctorsByClinicId();

    ResDTO updateDoctor(DoctorReqDto dto, MultipartFile file);

    ResDTO deleteDoctor(String id);

    DoctorEntity getDoctorEntityById(String id);
}
