package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import com.swacademy.mapcommunity.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public UUID save(UserDto userDto) {
        //1. dto -> Entity (준영속 상태 객체)
        User user = modelMapper.map(userDto, User.class);

        User entity = userRepository.save(user);

        return entity.getId();
    }

}
