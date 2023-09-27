package com.compny.workplace.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WorkPlaceResDto {
    private String id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
}
