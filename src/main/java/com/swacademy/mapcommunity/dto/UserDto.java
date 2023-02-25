package com.swacademy.mapcommunity.dto;


import com.swacademy.mapcommunity.domain.vo.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String email;
    private String password;
    private String nickName;
    private Gender gender;
    private LocalDate birth;

    private List<PostDto> posts;
    private List<CommentDto> comments;
}
