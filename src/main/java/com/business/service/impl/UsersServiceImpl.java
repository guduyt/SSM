package com.business.service.impl;

import com.business.service.UsersService;
import com.entity.mapper.RolesMapper;
import com.entity.mapper.UsersMapper;
import com.entity.model.Users;
import com.entity.model.UsersExample;
import com.yt.commons.Page;
import com.yt.commons.cache.ShardedJedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YT on 2016/1/22.
 */

@Service("Users")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RolesMapper rolesMapper;


    @Autowired
    private ShardedJedisCache redisCache;


    @Override
    public List<Users> getUsers() {
        UsersExample usersExample=new UsersExample();
        Page page=new Page();
        usersExample.setPage(page);
       return usersMapper.selectPageByExample(usersExample);
    }

    @Override
    public Users getById( String id) {

        return usersMapper.selectByPrimaryKey( id);
    }

    @Override
    public int insert(Users users){

        return usersMapper.insert(users);
    }

    @Override
    public int update(Users users){
        return usersMapper.updateByPrimaryKey(users);
    }

    @Override
    public int delete(Users users){
        return usersMapper.deleteByPrimaryKey(users.getId());
    }
}
