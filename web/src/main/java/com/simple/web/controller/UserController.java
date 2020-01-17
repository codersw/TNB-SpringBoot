package com.simple.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.simple.service.IUserService;
import com.simple.model.User;
import com.simple.model.PageRequest;
import com.simple.model.Result;
import com.simple.model.ResultGenerator;
import javax.annotation.Resource;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author swen
 * @since 2020-01-17
 */
@Api(value = "用户信息接口", tags = {"用户信息接口"})
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

     @Resource
     private IUserService userService;

     /**
     * 用户信息列表
     * @param user 用户信息
     * @param pageRequest 分页参数
     * @return Result
     */
     @ApiOperation(value = "用户信息列表", notes = "用户信息列表")
     @GetMapping("/list")
     public Result list(User user, PageRequest pageRequest) {
        return ResultGenerator.genSuccessResult(userService.userList(user, pageRequest));
     }
}
