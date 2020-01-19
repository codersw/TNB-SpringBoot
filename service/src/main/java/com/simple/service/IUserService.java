package com.simple.service;

import com.simple.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.model.PageRequest;
import com.simple.model.PageResponse;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author swen
 * @since 2020-01-19
 */
public interface IUserService extends IService<User> {

     PageResponse<User> userList(User user, PageRequest pageRequest);
}
