package com.swacademy.mapcommunity.domain.controller;

import com.swacademy.mapcommunity.ApiResponse;
import com.swacademy.mapcommunity.domain.service.UserService;
import com.swacademy.mapcommunity.dto.UserDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     *
     * @param e NotFoundException
     * @return ApiResponse.fail 404 Error
     */
    @ExceptionHandler(NotFoundException.class)
    public ApiResponse<String> notFoundHandler (NotFoundException e) {
        return ApiResponse.fail(404, e.getMessage());
    }

    /**
     *
     * @param e Exception
     * @return ApiResponse 505 fail
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> internalServerErrorHandler (Exception e) {
        return ApiResponse.fail(500, e.getMessage());
    }

    /**
     *
     * @param userDto UserDto
     * @return ApiResponse.ok
     */
    @PostMapping("/user")
    public ApiResponse<UUID> save(@RequestBody UserDto userDto) {
        UUID uuid = userService.save(userDto);
        return ApiResponse.ok(uuid);
    }

}



