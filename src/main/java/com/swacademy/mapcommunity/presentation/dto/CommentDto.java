package com.swacademy.mapcommunity.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {

    private Long id;
    private String content;
    private Integer commentLike;
    private Long postedId;
    private UserDto user;
    private PostDto post;

}
