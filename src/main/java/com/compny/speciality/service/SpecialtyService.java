package com.compny.speciality.service;

import com.compny.component.ResDTO;
import com.compny.speciality.dto.SpecialtyReqDto;
import com.compny.speciality.dto.SpecialtyResDto;
import com.compny.speciality.entity.SpecialtyEntity;

import java.util.List;

public interface SpecialtyService {

    ResDTO createSpecialty(SpecialtyReqDto dto);

    SpecialtyResDto getSpecialtyById(String id);

    List<SpecialtyResDto> getAllSpecialtiesByClinicId();

    ResDTO updateSpecialty(SpecialtyReqDto dto);

    ResDTO deleteSpecialty(String id);

    SpecialtyEntity getById(String id);
}
