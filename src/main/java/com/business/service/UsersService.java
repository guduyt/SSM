package com.business.service;

import com.entity.model.Users;

import java.util.List;

/**
 * Created by YT on 2016/1/22.
 */
public interface UsersService {

    List<Users> getUsers();
    Users getById(String id);
    int insert(Users users);
    int update(Users users);
    int delete(Users users);
}
