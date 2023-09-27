package com.compny.client.service;

import com.compny.client.dto.ClientReqDto;
import com.compny.client.dto.ClientResDto;
import com.compny.client.dto.ClientUpdDto;
import com.compny.component.ResDTO;

import java.util.List;


public interface ClientService {

    ClientResDto createClient(ClientReqDto dto);

    ClientResDto getClientById(String id);

    List<ClientResDto> getAllClientsByClinicId(String clinicId);

    ResDTO updateClient(ClientUpdDto dto);

    ResDTO deleteClient(String id);
}
