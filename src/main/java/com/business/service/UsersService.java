package com.business.service;

import com.entity.auto.model.SysUser;

import java.util.List;

/**
 * Created by YT on 2016/1/22.
 */
public interface UsersService {

    List<SysUser> getUsers();
    SysUser getById(long id);
    int insert(SysUser sysUsers);
    int update(SysUser sysUsers);
    int delete(SysUser sysUsers);
}
