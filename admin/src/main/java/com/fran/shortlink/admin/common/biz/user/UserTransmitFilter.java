package com.fran.shortlink.admin.common.biz.user;

import static com.fran.shortlink.admin.common.enums.UserErrorCodeEnum.USER_TOKEN_FAIL;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.fran.shortlink.admin.common.convention.exception.ClientException;
import com.fran.shortlink.admin.common.convention.result.Results;
import com.google.common.collect.Lists;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * User Info Transmitter Filter
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;

    private static final List<String> IGNORE_URI = Lists.newArrayList(
        "/api/short-link/admin/v1/user/login",
        "/api/short-link/admin/v1/actual/user/has-username",
        "/api/short-link/admin/v1/user/page"
        //"/api/short-link/admin/v1/user/logout",
        //"/api/short-link/admin/v1/user/has-username"
    );

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        if (!IGNORE_URI.contains(requestURI)) {
            String method = httpServletRequest.getMethod();
            if (!(Objects.equals(requestURI, "/api/short-link/admin/v1/user")) && !Objects.equals(
                method, "POST")) {
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if (!StrUtil.isAllNotBlank(username, token)) {
                    returnJson((HttpServletResponse) servletResponse,
                        JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;
                }
                Object userInfoJsonStr;
                try {
                    userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
                    if (userInfoJsonStr == null) {
                        throw new ClientException(USER_TOKEN_FAIL);
                    }
                } catch (Exception ex) {
                    returnJson((HttpServletResponse) servletResponse,
                        JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;
                }
                UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(),
                    UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }


        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }

    @SneakyThrows
    private void returnJson(HttpServletResponse response, String jsonStr) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {

            writer = response.getWriter();
            writer.write(jsonStr);
        } catch (IOException ex) {
        } finally {

            if (writer != null) {
                writer.close();
            }
        }
    }
}
