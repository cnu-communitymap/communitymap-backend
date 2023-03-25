package com.swacademy.mapcommunity.presentation.dto;

import com.swacademy.mapcommunity.data.entity.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private LocalDate birth;
    private List<PostDto> posts = new ArrayList<>();
    private List<CommentDto> comments = new ArrayList<>();

}
