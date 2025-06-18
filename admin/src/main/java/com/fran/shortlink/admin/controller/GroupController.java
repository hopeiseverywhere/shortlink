package com.fran.shortlink.admin.controller;

import com.fran.shortlink.admin.common.convention.result.Result;
import com.fran.shortlink.admin.common.convention.result.Results;
import com.fran.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.fran.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.fran.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.fran.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.fran.shortlink.admin.service.GroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PostMapping("/api/short-link/admin/v1/group/")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * Search short link group list
     */
    @GetMapping("/api/short-link/admin/v1/group/")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * Update short link group name
     */
    @PutMapping("/api/short-link/admin/v1/group/")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    /**
     * Delete short link group name
     */
    @DeleteMapping("/api/short-link/admin/v1/group/")
    public Result<Void> deleteGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    /**
     * Delete short link group name
     */
    @PostMapping("/api/short-link/admin/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }
}
