package com.swacademy.mapcommunity.presentation.mapper;

import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.presentation.dto.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("presentationPostMapper")
public class PostMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public PostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Post toEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    public PostDto toDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

}
