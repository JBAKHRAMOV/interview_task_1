package com.compny.client.controller;

import com.compny.client.dto.ClientReqDto;
import com.compny.client.dto.ClientResDto;
import com.compny.client.dto.ClientUpdDto;
import com.compny.client.service.ClientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Qualifier("client-service")
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientResDto> create(@RequestBody @Valid ClientReqDto dto) {
        return ResponseEntity.ok(clientService.createClient(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<?> getAllByClinicId(@PathVariable  String clinicId) {
        return ResponseEntity.ok(clientService.getAllClientsByClinicId(clinicId));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClient(@RequestBody @Valid ClientUpdDto dto) {
        return ResponseEntity.ok(clientService.updateClient(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable String id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }

}
