package com.compny.clinic.service;

import com.compny.clinic.dto.ClinicChangeStatusDto;
import com.compny.clinic.dto.ClinicReqDto;
import com.compny.clinic.dto.ClinicResDto;
import com.compny.clinic.dto.ClinicUpdDto;
import com.compny.clinic.entity.ClinicEntity;
import com.compny.component.ResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface ClinicService {
    ResDTO create(ClinicReqDto dto);

    ClinicResDto getById(String id);

    PageImpl<ClinicResDto> getAllByPagination(int page, int size);

    ResDTO updateClinic(ClinicUpdDto dto);

    ResDTO changeClinicStatus(ClinicChangeStatusDto dto);

    ClinicEntity getClinicById(String id);
}
