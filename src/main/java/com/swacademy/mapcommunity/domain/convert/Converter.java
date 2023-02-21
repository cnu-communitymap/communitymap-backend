package com.swacademy.mapcommunity.domain.convert;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public User convertUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getUuid());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setNickName(userDto.getNickName());
        user.setBirth(userDto.getBirth());

        return user;
    }

}
