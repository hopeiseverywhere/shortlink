package com.fran.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fran.shortlink.admin.common.convention.result.Result;
import com.fran.shortlink.admin.common.convention.result.Results;
import com.fran.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.fran.shortlink.admin.dto.resp.UserActualRespDTO;
import com.fran.shortlink.admin.dto.resp.UserRespDTO;
import com.fran.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Get user by username with sensitive info
     */
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        if (result == null) {
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code())
                .setMessage(UserErrorCodeEnum.USER_NULL.message());
        } else {
            return Results.success(result);
        }
    }

    /**
     * Get user by username with actual info
     */
    @GetMapping("/api/short-link/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(
        @PathVariable("username") String username) {
        return Results.success(
            BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * Check if a username existed or not
     */
    @GetMapping("/api/short-link/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }
}
