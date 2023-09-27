package com.compny.workplace.controller;

import com.compny.component.ResDTO;
import com.compny.workplace.dto.WorkPlaceReqDto;
import com.compny.workplace.dto.WorkPlaceResDto;
import com.compny.workplace.dto.WorkPlaceUpdDto;
import com.compny.workplace.service.WorkPlaceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/work-places")
public class WorkPlaceController {

    @Qualifier("work-place-service")
    private final WorkPlaceService workPlaceService;

    @PostMapping("/create")
    public ResponseEntity<ResDTO> createWorkPlace(@RequestBody @Valid WorkPlaceReqDto dto) {
        return ResponseEntity.ok(workPlaceService.createWorkPlace(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkPlaceResDto> getWorkPlaceById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(workPlaceService.getWorkPlaceById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkPlaceResDto>> getAllWorkPlacesByClinicId() {
        return ResponseEntity.ok(workPlaceService.getAllWorkPlacesByClinicId());
    }

    @PutMapping("/update")
    public ResponseEntity<ResDTO> updateWorkPlace(@RequestBody @Valid WorkPlaceUpdDto dto) {
        return ResponseEntity.ok(workPlaceService.updateWorkPlace(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResDTO> deleteWorkPlace(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(workPlaceService.deleteWorkPlace(id));
    }
}
