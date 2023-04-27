package com.swacademy.mapcommunity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()  //HTTP 기본인증 비활성화
                .csrf().disable()  //csrf 비활성
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 관리 방식 지정
                .and()
                .authorizeHttpRequests()
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .requestMatchers("/mapcommu/user/login/**").permitAll()
//                .requestMatchers("/mapcommu/user/signup/**").permitAll()
//                .requestMatchers("/mapcommu/user/mypage/**").hasRole("USER")
//                .requestMatchers("/mapcommu/post/read/**").permitAll()
                .anyRequest().permitAll()  //현재 모든 권한 필요없게 설정하였다.
//                     .anyRequest().hasRole("USER")
                .and()
                .headers().frameOptions().disable() //h2-console접근 위한
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}