package com.swacademy.mapcommunity.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String imageUrl;
    private Long postedUserId;
    private UserDto user;
    @JsonIgnore
    private List<CommentDto> comments = new ArrayList<>();

}
