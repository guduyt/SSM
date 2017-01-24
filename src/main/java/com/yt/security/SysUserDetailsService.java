package com.yt.security;

import com.entity.manual.model.Resource;
import com.entity.manual.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yt on 2016-10-17.
 */

@Service("sysUserDetailsService")
public class SysUserDetailsService implements UserDetailsService {

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      List<Resource> resourceList = userDAO.queryResourcesAndRoles(null);
        List<User> list=userDAO.queryUserAndRoles(username);
        if(!list.stream().findFirst().isPresent())  {
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }
        return list.stream().findFirst().get();
    }
}
