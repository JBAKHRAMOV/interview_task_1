package com.compny.workplace.service;

import com.compny.component.ResDTO;
import com.compny.workplace.dto.WorkPlaceReqDto;
import com.compny.workplace.dto.WorkPlaceResDto;
import com.compny.workplace.dto.WorkPlaceUpdDto;
import com.compny.workplace.entity.WorkPlaceEntity;

import java.util.List;

public interface WorkPlaceService {

    ResDTO createWorkPlace(WorkPlaceReqDto dto);

    WorkPlaceResDto getWorkPlaceById(String id);

    List<WorkPlaceResDto> getAllWorkPlacesByClinicId();

    ResDTO updateWorkPlace(WorkPlaceUpdDto dto);

    ResDTO deleteWorkPlace(String id);

    WorkPlaceEntity getById(String id);
}
