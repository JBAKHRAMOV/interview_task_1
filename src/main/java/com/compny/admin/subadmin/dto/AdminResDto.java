package com.compny.admin.subadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminResDto {
        private String id;
        private String fullName;
        private String email;
        private String clinicId;
}
