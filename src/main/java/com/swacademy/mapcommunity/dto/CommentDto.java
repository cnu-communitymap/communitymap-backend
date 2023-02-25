package com.swacademy.mapcommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private int id;
    private String content;
    private int commentLike;
    private LocalDateTime commentDatetime;

    private PostDto post;
    private UserDto user;
}
