package com.compny.admin.superadmin.service;

import com.compny.admin.superadmin.dto.SuperAdminReqDto;
import com.compny.component.ResDTO;

public interface SuperAdminService {

    ResDTO createSuperAdmin(SuperAdminReqDto dto);

    ResDTO updateSuperAdmin(SuperAdminReqDto dto);

    ResDTO deleteSuperAdmin(String id);

}
