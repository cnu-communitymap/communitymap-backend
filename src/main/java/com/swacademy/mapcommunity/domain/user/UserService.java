package com.swacademy.mapcommunity.domain.user;

import com.swacademy.mapcommunity.data.UserRepository;
import com.swacademy.mapcommunity.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //회원 조회.
    User getUser(UUID userId){
        return userRepository.getUser(userId);
    }

    //@ToDo 회원가입 및 아이디 중복 검사
    public UUID join(User user) throws Exception {
        if (validateDuplicatedMember(user)) UserRepository.register(user);
        else throw new Exception();

        return user.getUserId();
    }
    //@ToDo 이미 존재하는 회원인지 검사
    private boolean validateDuplicatedMember(User user){
        List<User> findMembers = (List<User>) userRepository.getUser(user.getUserId());
//        if(!findMembers.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
        return findMembers.isEmpty();
    }

    //@ToDo 회원 정보 update 메소드 service에 필요한가?

}
