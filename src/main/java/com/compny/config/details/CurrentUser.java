package com.compny.config.details;

import com.compny.base.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CurrentUser {
    private String id;
    private String email;
    private String clinicId;
    private Role role;
}
