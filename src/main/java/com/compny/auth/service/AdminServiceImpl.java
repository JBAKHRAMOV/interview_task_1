package com.compny.auth.service;

import com.compny.admin.subadmin.entity.AdminEntity;
import com.compny.admin.subadmin.repository.AdminRepository;
import com.compny.admin.superadmin.entity.SuperAdminEntity;
import com.compny.admin.superadmin.repository.SuperAdminRepository;
import com.compny.auth.dto.AdminLoginDto;
import com.compny.auth.dto.ForgotPasswordDto;
import com.compny.auth.dto.TokenDto;
import com.compny.base.enums.Role;
import com.compny.clinic.entity.ClinicEntity;
import com.compny.clinic.repository.ClinicRepository;
import com.compny.component.JwtDecode;
import com.compny.component.ResDTO;
import com.compny.config.JwtUtil;
import com.compny.exception.AppBadRequestException;
import com.compny.exception.ItemNotFoundException;
import com.compny.sms.service.SmsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service("admin-auth-service")
public class AdminServiceImpl implements AdminAuthService {

    private final AdminRepository adminRepository;
    private final ClinicRepository clinicRepository;
    private final SuperAdminRepository superAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private final SmsService smsService;

    String DOMAIN = "http://localhost:8080";

    @Override
    public TokenDto login(AdminLoginDto dto) {

        String id;
        Role role;

        if (isValidEmail(dto.getKeyWord())) {
            SuperAdminEntity superAdmin = superAdminRepository.findByEmail(dto.getKeyWord())
                    .orElseThrow(() -> new ItemNotFoundException("Password or Email is incorrect"));
            if (!passwordEncoder.matches(dto.getPassword(), superAdmin.getPassword()))
                throw new ItemNotFoundException("Password or Email is incorrect");
            id = superAdmin.getId();
            role = superAdmin.getRole();
        } else {
            ClinicEntity clinic = clinicRepository.findByIpAddress(dto.getKeyWord())
                    .orElseThrow(() -> new ItemNotFoundException("Password or IPAddress is incorrect"));

            AdminEntity admin = adminRepository.findByPasswordAndClinicId(dto.getPassword(), clinic.getId())
                    .orElseThrow(() -> new ItemNotFoundException("Password or IPAddress is incorrect"));

            id = admin.getId();
            role = admin.getRole();
        }


        return new TokenDto(JwtUtil.encode(id, role));
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResDTO forgotPassword(ForgotPasswordDto dto) {

        String id;
        Role role;
        Optional<SuperAdminEntity> superAdmin = superAdminRepository.findByEmail(dto.getEmail());
        Optional<AdminEntity> admin = adminRepository.findByEmail(dto.getEmail());

        if (superAdmin.isPresent()) {
            id = superAdmin.get().getId();
            role = superAdmin.get().getRole();
        } else if (admin.isPresent()) {
            id = admin.get().getId();
            role = admin.get().getRole();
        } else {
            throw new ItemNotFoundException("Admin Not Found");
        }

        TokenDto tokenDto = new TokenDto(JwtUtil.encode(id, role));

        String text = DOMAIN + "/api/v1/auth/confirm-password/" + tokenDto.getToken();

        smsService.sendSmsToEmail(dto.getEmail(), tokenDto.getToken());

        return new ResDTO("Sms code send to email");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResDTO confirmPasswd(String token) {

        JwtDecode decode = JwtUtil.decode(token);

        String randPassword = "123456";
        String email;

        if (decode.getRole().equals(Role.ROLE_SUPER_ADMIN)) {
            SuperAdminEntity superAdmin = superAdminRepository.findById(decode.getId())
                    .orElseThrow(() -> new ItemNotFoundException("Admin Not Found"));

            email = superAdmin.getEmail();

            superAdmin.setPassword(passwordEncoder.encode(randPassword));

            superAdminRepository.save(superAdmin);

        } else if (decode.getRole().equals(Role.ROLE_ADMIN)) {
            AdminEntity admin = adminRepository.findById(decode.getId())
                    .orElseThrow(() -> new ItemNotFoundException("Admin Not Found"));

            email = admin.getEmail();

            admin.setPassword(passwordEncoder.encode(randPassword));

            adminRepository.save(admin);
        }else {
            throw new AppBadRequestException("Role not found");
        }


        String text = "Your password changed. " +
                    "Your password " + randPassword
                + " Please change your password after login";

        smsService.sendSmsToEmail(email, text);

        return new ResDTO("Password changed ");
    }

    private static boolean isValidEmail(String email) {

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }


}
