package com.swacademy.mapcommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private int postId;
    private String title;
    private String content;
    private LocalDateTime postDatetime;
    private int postLike;
    private Point geography;

    private UserDto userDto;
    private List<CommentDto> commentDtos;
}
