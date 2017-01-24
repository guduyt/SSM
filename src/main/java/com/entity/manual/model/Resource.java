package com.entity.manual.model;

import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yt on 2016-10-17.
 */
public class Resource implements Serializable{

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private Integer id;

    private String resourcesName;

    private Byte resourcesType;

    private String resourcePath;

    private String description;

    private Boolean enable;

    private Integer parentId;

    private List<Role> listRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public Byte getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(Byte resourcesType) {
        this.resourcesType = resourcesType;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getParentId() {
        return parentId;
    }



    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Role> getListRole() {
        return listRole;
    }

    public void setListRole(List<Role> listRole) {
        this.listRole = listRole;
    }
}
