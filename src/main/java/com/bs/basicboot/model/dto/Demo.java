package com.bs.basicboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demo {
    private String devName;
    private String devGender;
    private Integer devAge;
    private String devLang;
}
