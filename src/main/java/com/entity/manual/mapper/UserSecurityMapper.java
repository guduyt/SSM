package com.entity.manual.mapper;

import com.entity.manual.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yt on 2017-1-23.
 */
public interface UserSecurityMapper {

    List<User> queryUserAndRoles(@Param("username") String username);
    List<User> queryAllUserAndRoles();
}
