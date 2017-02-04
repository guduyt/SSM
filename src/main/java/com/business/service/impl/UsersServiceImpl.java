package com.business.service.impl;

import com.business.service.UsersService;
import com.entity.auto.mapper.SysUserMapper;
import com.entity.auto.model.SysUser;
import com.entity.auto.model.SysUserExample;
import com.yt.commons.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YT on 2016/1/22.
 */

@Service("Users")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private SysUserMapper usersMapper;

    @Override
    public List<SysUser> getUsers() {
        SysUserExample usersExample = new SysUserExample();
        Page page = new Page();
        usersExample.setCurrentPage(page.getCurrentPage());
        usersExample.setPageSize(page.getPageSize());
        return usersMapper.selectPageByExample(usersExample);
    }

    @Override
    public SysUser getById(long id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(SysUser users) {
        return usersMapper.insert(users);
    }

    @Override
    public int update(SysUser users) {
        return usersMapper.updateByPrimaryKey(users);
    }

    @Override
    public int delete(SysUser users) {
        return usersMapper.deleteByPrimaryKey(users.getId());
    }
}
