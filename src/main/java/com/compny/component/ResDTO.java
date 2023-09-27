package com.compny.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResDTO {
    private Boolean status = true;
    private String massage;

    public ResDTO(String massage) {
        this.massage = massage;
    }
}
