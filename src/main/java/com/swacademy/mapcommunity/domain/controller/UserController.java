package com.swacademy.mapcommunity.domain.controller;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.mapper.UserMapper;
import com.swacademy.mapcommunity.domain.service.UserService;
import com.swacademy.mapcommunity.domain.vo.LoginInfo;
import com.swacademy.mapcommunity.dto.UserDto;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//@Todo user validate 검사
//@Todo pw 암호화 필요

@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.userMapper = new UserMapper(modelMapper);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundHandler (NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchFieldError.class)
    public ResponseEntity<String> NoSuchHandler (NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    /**
     * 회원가입
     * @param userDto UserDto 프론트로부터 JSON파일로 받는다.
     * @return ResponseEntity
     */
    @PostMapping(value = "/register")
    public ResponseEntity<UUID> register(@RequestBody UserDto userDto) {

        User entity = userMapper.dtoToEntityUser(userDto);

        UUID uuid = userService.save(entity);

        return ResponseEntity.ok(uuid);
    }

    /**
     * 로그인 //@Todo springSecurity 사용하여 Authentication 등 처리해야함
     * @param loginInfo LoginInfo
     * @return UserDto
     * @throws NotFoundException -
     */
    @Transactional
    @PostMapping(value = "/login")
    public UserDto login(@RequestBody LoginInfo loginInfo) throws NotFoundException {
        //@Todo login 하면 UUID만 넘겨줄지 or UserDto를 넘겨줄지 나중에 결정

        User entity = userService.login(loginInfo.email(), loginInfo.password());

        return userMapper.entityToDtoUser(entity);
    }

    /**
     * 로그인 하면 uuid를 돌려주고 받은 uuid로 mypage에 접근  아마 security 쪽에서 해결될듯?
     * @param uuid UUID
     * @return UserDto
     */
    @Transactional
    @PostMapping(value = "/mypage")
    public UserDto mypage(@RequestParam UUID uuid) throws NotFoundException {
        User entity = userService.mypage(uuid);

        return userMapper.entityToDtoUser(entity);
    }

    @Transactional
    @PostMapping(value = "/update")
    public ResponseEntity<UUID> update(@RequestBody UserDto userDto) {

        User entity = userMapper.dtoToEntityUser(userDto);

        UUID uuid = userService.update(entity);

        return ResponseEntity.ok(uuid);
    }

    //@Todo Authentication 추가 + Spring security
    //@Todo SpringSecurity 사용 -> API 보호 & API 호출자 권한 체크 후 권한 없는 경우 API 호출 거부
    @Transactional
    @PostMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.ok("Delete success");
    }

}
