package com.swacademy.mapcommunity.presentation.mapper;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.presentation.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("presentationUserMapper")
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper( ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
