package com.entity.mapper;

import com.entity.model.Users;
import com.entity.model.UsersExample;
import com.yt.mybatis.model.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper extends com.yt.mybatis.model.BaseMapper<java.lang.String, com.entity.model.Users, com.entity.model.UsersExample> {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}