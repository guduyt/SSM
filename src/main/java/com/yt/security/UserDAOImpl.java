package com.yt.security;

import com.entity.manual.mapper.ResourceSecurityMapper;
import com.entity.manual.mapper.UserSecurityMapper;
import com.entity.manual.model.Resource;
import com.entity.manual.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yt on 2016-10-17.
 */
@Service("userDAOImpl")
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserSecurityMapper userSecurityMapper;
    @Autowired
    private ResourceSecurityMapper resourceSecurityMapper;

    @Override
    public List<User> queryUserAndRoles(String username) {

        return userSecurityMapper.queryUserAndRoles(username);
    }

    @Override
    public List<Resource> queryResourcesAndRoles(String name) {
        return resourceSecurityMapper.queryResourcesAndRoles(name);
    }

    @Override
    public List<Resource> queryResourcesAndRoles() {
        return  resourceSecurityMapper.queryAllResourcesAndRoles();
    }
}
