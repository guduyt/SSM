package com.yt.security;

import com.entity.manual.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by yt on 2016-10-17.
 */

@Service("sysUserDetailsService")
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        List<User> list=userDAO.queryUserAndRoles(username);
        Optional<User> user= list.stream().findFirst();
        if(!user.isPresent())  {
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }
        return user.get();
    }
}
