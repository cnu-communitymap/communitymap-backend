package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.config.JwtTokenProvider;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.swacademy.mapcommunity.presentation.dto.TokenInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Long saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Password encryption
        user.setPassword(encodedPassword);
        return userRepository.insertUser(user);
    }

    public User getUserById(Long userId) {
        return userRepository.selectUserById(userId);
    }

    public User getUserWithPostById(Long userId) {
        return userRepository.selectUserById(userId, true, false);
    }

    public User getUserWithCommentById(Long userId) {
        return userRepository.selectUserById(userId, false, true);
    }

    public User getUserWithPostAndCommentById(Long userId) {
        return userRepository.selectUserById(userId, true, true);
    }

    public List<Post> getUserPostsByUserId(Long userId) {
        return userRepository.selectPostsByUserId(userId);
    }

    public List<Comment> getUserCommentsByUserId(Long userId) {
        return userRepository.selectCommentsByUserId(userId);
    }

    public Long updateUser(User updatedUser) throws IOException {
        return userRepository.updateUser(updatedUser);
    }
    
    public void deleteUserById(Long userId) throws IOException {
        userRepository.deleteUserById(userId);
    }

    @Transactional()
    public TokenInfo login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 검증된 인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }
    /**
     * if logged -> return User
     * else -> return null;
     * @return User
     */
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            return userRepository.selectUserByEmail(email);
        }
        return null;
    }
}
