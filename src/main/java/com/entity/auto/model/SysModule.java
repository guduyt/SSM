package com.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "sys_module")
public class SysModule extends BaseModel implements Serializable {
    /**
    * 模块主键
    */
    @Column(name="id")
    @Id
    private Integer id;

    /**
    * 模块名称
    */
    @Column(name="module_name")
    private String moduleName;

    /**
    * 模块类型
    */
    @Column(name="module_type")
    private Byte moduleType;

    /**
    * 描述
    */
    @Column(name="description")
    private String description;

    /**
    * 模块路径
    */
    @Column(name="module_path")
    private String modulePath;

    /**
    * 父级模块
    */
    @Column(name="parent_id")
    private Integer parentId;

    /**
    * 级别
    */
    @Column(name="level")
    private Boolean level;

    /**
    * 应用名称
    */
    @Column(name="application_name")
    private String applicationName;

    /**
    * 控制器名称
    */
    @Column(name="controller_name")
    private String controllerName;

    /**
    * 启用标志
    */
    @Column(name="enable")
    private Boolean enable;

    /**
    * 备注
    */
    @Column(name="remark")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public Byte getModuleType() {
        return moduleType;
    }

    public void setModuleType(Byte moduleType) {
        this.moduleType = moduleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath == null ? null : modulePath.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getLevel() {
        return level;
    }

    public void setLevel(Boolean level) {
        this.level = level;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName == null ? null : controllerName.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}