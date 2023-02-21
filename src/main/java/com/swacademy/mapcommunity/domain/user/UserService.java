package com.swacademy.mapcommunity.domain.user;

import com.swacademy.mapcommunity.domain.convert.Converter;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import com.swacademy.mapcommunity.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Converter converter;

    @Transactional
    public UUID save(UserDto userDtodto) {
        //1. dto -> Entity (준영속 상태 객체)
        User user = converter.convertUser(userDtodto);

        //2. userRepository.save(entity)  -> 영속화
        User entity = userRepository.save(user);

        //3. 반환
        return entity.getId();
    }

}
