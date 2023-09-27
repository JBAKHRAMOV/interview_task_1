package com.compny.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientResDto {
    private String id;
    private String fullName;
    private String phone;
    private String otherInfo;
}
