package com.swacademy.mapcommunity.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenInfo {
    private String grantType; //JWT 대한 인증 타입 이후 HTTP헤더에 prefix로 붙여주는 타입
    private String accessToken;
    private String refreshToken;
}