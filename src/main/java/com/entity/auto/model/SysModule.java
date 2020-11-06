package com.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

/**
* 数据表:sys_module
* 菜单模块表
*/
public class SysModule extends BaseModel implements Serializable {
    /**
    * 字段:sys_module.id
    * 模块主键
    */
    private Integer id;

    /**
    * 字段:sys_module.app_id
    * 应用名称
    */
    private Integer appId;

    /**
    * 字段:sys_module.module_name
    * 模块名称
    */
    private String moduleName;

    /**
    * 字段:sys_module.module_type
    * 模块类型
    */
    private Byte moduleType;

    /**
    * 字段:sys_module.description
    * 描述
    */
    private String description;

    /**
    * 字段:sys_module.module_path
    * 模块路径
    */
    private String modulePath;

    /**
    * 字段:sys_module.parent_id
    * 父级模块
    */
    private Integer parentId;

    /**
    * 字段:sys_module.level
    * 级别
    */
    private Boolean level;

    /**
    * 字段:sys_module.controller_name
    * 控制器名称
    */
    private String controllerName;

    /**
    * 字段:sys_module.enable
    * 启用标志
    */
    private Boolean enable;

    /**
    * 字段:sys_module.order
    * 排序
    */
    private Byte order;

    /**
    * 字段:sys_module.remark
    * 备注
    */
    private String remark;

    /**
    * 字段:sys_module.creator
    * 创建人
    */
    private String creator;

    /**
    * 字段:sys_module.last_time
    * 最后更新时间
    */
    private Date lastTime;

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

    public Byte getOrder() {
        return order;
    }

    public void setOrder(Byte order) {
        this.order = order;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}