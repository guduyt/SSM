package com.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "sys_role")
public class SysRole extends BaseModel implements Serializable {
    /**
    * 角色主键
    */
    @Column(name="id")
    @Id
    private Integer id;

    /**
    * 角色名
    */
    @Column(name="role_name")
    private String roleName;

    /**
    * 描述
    */
    @Column(name="description")
    private String description;

    /**
    * 启用标志
    */
    @Column(name="enable")
    private Boolean enable;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}