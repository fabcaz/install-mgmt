package com.example.installmgmt.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HardwareDto {

    private Integer id;

    @NotBlank
    private String name;

}
