package com.fran.shortlink.admin.controller;

import com.fran.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Short Link Group Controller
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
}
