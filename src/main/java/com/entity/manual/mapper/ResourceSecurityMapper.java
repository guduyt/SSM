package com.entity.manual.mapper;

import com.entity.manual.model.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yt on 2017-1-23.
 */
public interface ResourceSecurityMapper {
    List<Resource> queryResourcesAndRoles(@Param("resourcesName") String resourcesName);
    List<Resource> queryAllResourcesAndRoles();
}
