package com.compny.component;

import com.compny.base.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtDecode {
    private String id;
    private Role role;
}
