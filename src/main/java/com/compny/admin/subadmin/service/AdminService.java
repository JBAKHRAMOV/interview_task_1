package com.compny.admin.subadmin.service;

import com.compny.admin.subadmin.dto.AdminReqDto;
import com.compny.admin.subadmin.dto.AdminResDto;
import com.compny.base.enums.Status;
import com.compny.component.ResDTO;
import org.springframework.data.domain.PageImpl;

public interface AdminService {

    ResDTO createAdmin(AdminReqDto dto);

    AdminResDto getAdmin(String id);

    PageImpl<AdminResDto> getAllByPagination(int page, int size);

    ResDTO updateAdmin(AdminReqDto dto);

    ResDTO logout();

    void createAdmin(String fullName, String email, String password, String clinicId);

    void changeAdminStatus(String clinicId, Status status);

    void sendMessageAllAdminOfClinic(String clinicId, String message);

}
