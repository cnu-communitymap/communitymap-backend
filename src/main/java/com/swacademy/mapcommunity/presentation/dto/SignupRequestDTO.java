package com.swacademy.mapcommunity.presentation.dto;

import com.swacademy.mapcommunity.data.entity.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter @Setter
public class SignupRequestDTO {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private LocalDate birth;
}
