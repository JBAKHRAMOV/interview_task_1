package com.compny.config.details;


import com.compny.admin.subadmin.entity.AdminEntity;
import com.compny.admin.subadmin.repository.AdminRepository;
import com.compny.admin.superadmin.entity.SuperAdminEntity;
import com.compny.admin.superadmin.repository.SuperAdminRepository;
import com.compny.base.enums.Role;
import com.compny.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomProfileDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final SuperAdminRepository superAdminRepository;

    @Override
    public CustomProfileDetails loadUserByUsername(String id){
        SuperAdminEntity entity = superAdminRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Profile Not Found"));

        return new CustomProfileDetails(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                "",
                entity.getRole()
        );
    }


    public CustomProfileDetails loadUserByUsernameFromAdmin(String id){

        AdminEntity entity = adminRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Profile Not Found"));

        return new CustomProfileDetails(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getClinicId(),
                entity.getRole()
        );
    }
}
