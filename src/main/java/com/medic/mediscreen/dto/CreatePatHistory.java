package com.medic.mediscreen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Setter
@Getter
public class CreatePatHistory {
    String note;
    Integer id;
}
