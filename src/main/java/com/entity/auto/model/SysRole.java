package com.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;

/**
* 数据表:sys_role
* 角色表
*/
public class SysRole extends BaseModel implements Serializable {
    /**
    * 字段:sys_role.id
    * 角色主键
    */
    private Integer id;

    /**
    * 字段:sys_role.app_id
    * 应用id
    */
    private Integer appId;

    /**
    * 字段:sys_role.role_name
    * 角色名
    */
    private String roleName;

    /**
    * 字段:sys_role.description
    * 描述
    */
    private String description;

    /**
    * 字段:sys_role.enable
    * 启用标志
    */
    private Boolean enable;

    /**
    * 字段:sys_role.order
    * 排序
    */
    private Byte order;

    /**
    * 字段:sys_role.creator
    * 创建人
    */
    private String creator;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Byte getOrder() {
        return order;
    }

    public void setOrder(Byte order) {
        this.order = order;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}