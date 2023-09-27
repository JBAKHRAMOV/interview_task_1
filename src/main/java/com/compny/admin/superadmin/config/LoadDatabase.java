package com.compny.admin.superadmin.config;


import com.compny.admin.superadmin.entity.SuperAdminEntity;
import com.compny.admin.superadmin.repository.SuperAdminRepository;
import com.compny.base.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {
    private final SuperAdminRepository superAdminRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            var superAdmin = new SuperAdminEntity();
            superAdmin.setEmail("superadmin@gmail.com");
            superAdmin.setFullName("superAdmin 1");
            superAdmin.setPassword(new BCryptPasswordEncoder().encode("password"));
            superAdmin.setRole(Role.ROLE_SUPER_ADMIN);
            if(superAdminRepository.findByEmail(superAdmin.getEmail()).isEmpty()) superAdminRepository.save(superAdmin);
        };
        }
}
