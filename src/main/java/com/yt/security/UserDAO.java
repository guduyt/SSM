package com.yt.security;

import com.entity.manual.model.Resource;
import com.entity.manual.model.User;

import java.util.List;

/**
 * Created by yt on 2016-10-17.
 */
public interface UserDAO {
    public List<User> queryUserAndRoles(String username);

    public List<Resource> queryResourcesAndRoles(String name) ;

    public List<Resource> queryResourcesAndRoles();
}
