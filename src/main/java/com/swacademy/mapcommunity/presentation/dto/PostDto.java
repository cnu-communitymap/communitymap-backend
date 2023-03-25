package com.swacademy.mapcommunity.presentation.dto;

import com.swacademy.mapcommunity.domain.entity.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Integer postLike;
    private Location position;
    private UserDto user;
    private List<CommentDto> comments = new ArrayList<>();

}
