package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UUID save(User user) {

        User entity = userRepository.save(user);

        return entity.getId();
    }

    //@Todo SpringSecurity -> Authentication 추가. pw 검사도
    @Transactional
    public User login(String email, String password) throws NoSuchElementException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found with email " + email));

    }

    @Transactional
    public User mypage(UUID uuid) throws NoSuchElementException {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("User not found with uuid" + uuid));

    }

    //@Todo return -> UUID or Dto 생각
    @Transactional
    public UUID update(User user) {
        return userRepository.save(user).getId();
    }

    @Transactional
    public void delete(UUID uuid) {
        userRepository.deleteById(uuid);
    }

}
