package com.fran.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fran.shortlink.project.common.convention.result.Result;
import com.fran.shortlink.project.common.convention.result.Results;
import com.fran.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.fran.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.fran.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Short Link Controller
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * Create Short Link
     */
    @PostMapping("/api/short-link/v1/create")
    @SneakyThrows
    public Result<ShortLinkCreateRespDTO> createShortLink(
        @RequestBody ShortLinkCreateReqDTO requestParam) {

        return Results.success(shortLinkService.createShortUrl(requestParam));
    }

    /**
     * Query Short Link by Page
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

}
