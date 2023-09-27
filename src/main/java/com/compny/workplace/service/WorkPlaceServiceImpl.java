package com.compny.workplace.service;

import com.compny.component.ResDTO;
import com.compny.config.details.EntityDetails;
import com.compny.exception.ItemNotFoundException;
import com.compny.workplace.dto.WorkPlaceReqDto;
import com.compny.workplace.dto.WorkPlaceResDto;
import com.compny.workplace.dto.WorkPlaceUpdDto;
import com.compny.workplace.entity.WorkPlaceEntity;
import com.compny.workplace.repository.WorkPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("work-place-service")
public class WorkPlaceServiceImpl implements WorkPlaceService{

    private final WorkPlaceRepository workPlaceRepository;

    @Override
    public ResDTO createWorkPlace(WorkPlaceReqDto dto) {

        workPlaceRepository.findByName(dto.getName())
                .ifPresent(workPlaceEntity -> {
                    throw new ItemNotFoundException("WorkPlace already exists");
                });

        WorkPlaceEntity entity = new WorkPlaceEntity();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());

        workPlaceRepository.save(entity);

        return new ResDTO("WorkPlace created");
    }

    @Override
    public WorkPlaceResDto getWorkPlaceById(String id) {
        return entityToDto(getWorkPlace(id));
    }

    @Override
    public List<WorkPlaceResDto> getAllWorkPlacesByClinicId() {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return workPlaceRepository.findAllByClinicId(clinicId)
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public ResDTO updateWorkPlace(WorkPlaceUpdDto dto) {

            WorkPlaceEntity workPlace = getWorkPlace(dto.getId());
            workPlace.setName(dto.getName());
            workPlace.setAddress(dto.getAddress());
            workPlace.setLatitude(dto.getLatitude());
            workPlace.setLongitude(dto.getLongitude());

            workPlaceRepository.save(workPlace);

            return new ResDTO("WorkPlace updated");
    }

    @Override
    public ResDTO deleteWorkPlace(String id) {

            WorkPlaceEntity workPlace = getWorkPlace(id);

            workPlace.setIsDeleted(true);

            workPlaceRepository.save(workPlace);

            return new ResDTO("WorkPlace deleted");
    }

    @Override
    public WorkPlaceEntity getById(String id) {
        return getWorkPlace(id);
    }

    private WorkPlaceResDto entityToDto(WorkPlaceEntity entity) {
        return WorkPlaceResDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }

    private WorkPlaceEntity getWorkPlace(String id) {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return workPlaceRepository.findByIdAndIsDeletedAndClinicId(id, false, clinicId)
                .orElseThrow(() -> new ItemNotFoundException("WorkPlace not found"));
    }
}
