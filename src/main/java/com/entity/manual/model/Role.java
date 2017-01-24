package com.entity.manual.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by yt on 2017-1-23.
 */
public class Role implements GrantedAuthority {
    private Integer id;

    private String roleName;


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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}