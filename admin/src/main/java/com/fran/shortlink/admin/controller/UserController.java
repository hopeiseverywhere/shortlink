package com.fran.shortlink.admin.controller;

import com.fran.shortlink.admin.common.convention.result.Result;
import com.fran.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.fran.shortlink.admin.dto.resp.UserRespDTO;
import com.fran.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        if (result == null) {
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code())
                .setMessage(UserErrorCodeEnum.USER_NULL.message());
        } else {
            return new Result<UserRespDTO>().setCode("0")
                .setData(userService.getUserByUsername(username));
        }
    }
}
