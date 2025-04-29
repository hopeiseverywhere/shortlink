package com.fran.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fran.shortlink.admin.common.convention.result.Result;
import com.fran.shortlink.admin.common.convention.result.Results;
import com.fran.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.fran.shortlink.admin.dto.req.UserLoginReqDTO;
import com.fran.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.fran.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.fran.shortlink.admin.dto.resp.UserActualRespDTO;
import com.fran.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.fran.shortlink.admin.dto.resp.UserRespDTO;
import com.fran.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping("/api/short-link/admin/v1/user/{username}")
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
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(
        @PathVariable("username") String username) {
        return Results.success(
            BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * Check if a username existed or not
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * User registration
     */
    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> registerUser(@RequestBody UserRegisterReqDTO requestParam) {
        userService.registerUser(requestParam);
        return Results.success();
    }

    /**
     * Update user
     */
    @PutMapping("/api/short-link/admin/v1/user")
    public Result<Void> updateUser(@RequestBody UserUpdateReqDTO requestParam) {
        userService.updateUser(requestParam);
        return Results.success();
    }

    /**
     * User login
     */
    @PostMapping("/api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        UserLoginRespDTO result = userService.login(requestParam);
        return Results.success(result);
    }

    /**
     * Check if the user has logged in or not
     */
    @GetMapping("/api/short-link/admin/v1/user/login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username,
        @RequestParam("token") String token) {
        Boolean result = userService.checkLogin(username, token);
        return Results.success(result);
    }
}
