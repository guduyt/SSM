package com.entity.mapper;

import com.entity.model.Roles;
import com.entity.model.RolesExample;
import com.yt.mybatis.model.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesMapper extends com.yt.mybatis.model.BaseMapper<java.lang.Integer, com.entity.model.Roles, com.entity.model.RolesExample> {
    int countByExample(RolesExample example);

    int deleteByExample(RolesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    List<Roles> selectByExample(RolesExample example);

    Roles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Roles record, @Param("example") RolesExample example);

    int updateByExample(@Param("record") Roles record, @Param("example") RolesExample example);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}