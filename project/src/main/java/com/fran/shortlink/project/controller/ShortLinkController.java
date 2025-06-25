package com.fran.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fran.shortlink.project.common.convention.result.Result;
import com.fran.shortlink.project.common.convention.result.Results;
import com.fran.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.fran.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.fran.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.fran.shortlink.project.service.ShortLinkService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
     *
     * @param requestParam params for creating a short link
     * @return short link creation info
     */
    @PostMapping("/api/short-link/v1/create")
    @SneakyThrows
    public Result<ShortLinkCreateRespDTO> createShortLink(
        @RequestBody ShortLinkCreateReqDTO requestParam) {

        return Results.success(shortLinkService.createShortUrl(requestParam));
    }

    /**
     * Query Short Link by Page
     *
     * @param requestParam params for query short link by page
     * @return short link query result by page
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * Update Short Link
     * @param requestParam params for updating short link
     */
    @PutMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {

        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * Query Short Link Count under a group
     */
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(
        @RequestParam("requestParam") List<String> requestParam) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }
}
