package com.compny.config.details;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class EntityDetails {
    public static CurrentUser getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomProfileDetails details = (CustomProfileDetails) authentication.getPrincipal();
        return details.getCurrentUser();
    }
}
