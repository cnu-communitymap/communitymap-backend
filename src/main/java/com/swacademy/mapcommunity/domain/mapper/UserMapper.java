package com.swacademy.mapcommunity.domain.mapper;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.dto.UserDto;
import org.modelmapper.ModelMapper;

public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper =modelMapper;
    }

    /**
     * Entity -> Dto
     * @param user User Entity
     * @return UserDto
     */
    public UserDto entityToDtoUser(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * Dto -> Entity
     * @param userDto UserDto
     * @return User Entity
     */
    public User dtoToEntityUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


}
