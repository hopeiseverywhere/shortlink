package com.fran.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fran.shortlink.admin.common.convention.result.Result;
import com.fran.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.fran.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.fran.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.fran.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.fran.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Short Link Backend Controller
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {

        };
     /**
     * Create Short Link
     */
    @PostMapping("/api/short-link/admin/v1/create")
    @SneakyThrows
    public Result<ShortLinkCreateRespDTO> createShortLink(
        @RequestBody ShortLinkCreateReqDTO requestParam) {

        return shortLinkRemoteService.createShortUrl(requestParam);
    }
    /**
     * Query Short Link by Page
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {

        return shortLinkRemoteService.pageShortLink(requestParam);
    }

}
