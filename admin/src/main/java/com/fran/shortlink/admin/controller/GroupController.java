package com.fran.shortlink.admin.controller;

import com.fran.shortlink.admin.common.convention.result.Result;
import com.fran.shortlink.admin.common.convention.result.Results;
import com.fran.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.fran.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.fran.shortlink.admin.service.GroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Short Link Group Controller
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * Save short link group
     */
    @PostMapping("/api/short-link/v1/group/")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping("/api/short-link/v1/group/")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {


        return Results.success(groupService.listGroup());
    }
}
