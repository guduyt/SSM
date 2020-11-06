package com.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

/**
* 数据表:sys_resource
* 资源表
*/
public class SysResource extends BaseModel implements Serializable {
    /**
    * 字段:sys_resource.id
    * 资源主键
    */
    private Integer id;

    /**
    * 字段:sys_resource.app_id
    * 应用id
    */
    private Integer appId;

    /**
    * 字段:sys_resource.resources_name
    * 资源名
    */
    private String resourcesName;

    /**
    * 字段:sys_resource.resource_path
    * 资源url
    */
    private String resourcePath;

    /**
    * 字段:sys_resource.request_path
    * 请求url
    */
    private String requestPath;

    /**
    * 字段:sys_resource.request_type
    * 请求方式
    */
    private String requestType;

    /**
    * 字段:sys_resource.java_class
    * java类名
    */
    private String javaClass;

    /**
    * 字段:sys_resource.short_method
    * 方法名称
    */
    private String shortMethod;

    /**
    * 字段:sys_resource.method
    * 包名、类名、参数等信息的完整方法名
    */
    private String method;

    /**
    * 字段:sys_resource.description
    * 描述
    */
    private String description;

    /**
    * 字段:sys_resource.enable
    * 启用标志
    */
    private Boolean enable;

    /**
    * 字段:sys_resource.resources_type
    * 资源类型
    */
    private Byte resourcesType;

    /**
    * 字段:sys_resource.parent_id
    * 父级资源
    */
    private Integer parentId;

    /**
    * 字段:sys_resource.remark
    * 备注
    */
    private String remark;

    /**
    * 字段:sys_resource.creator
    * 创建人
    */
    private String creator;

    /**
    * 字段:sys_resource.create_time
    * 创建时间
    */
    private Date createTime;

    /**
    * 字段:sys_resource.editor
    * 修改人
    */
    private String editor;

    /**
    * 字段:sys_resource.edit_time
    * 修改时间
    */
    private Date editTime;

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

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName == null ? null : resourcesName.trim();
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath == null ? null : requestPath.trim();
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass == null ? null : javaClass.trim();
    }

    public String getShortMethod() {
        return shortMethod;
    }

    public void setShortMethod(String shortMethod) {
        this.shortMethod = shortMethod == null ? null : shortMethod.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
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

    public Byte getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(Byte resourcesType) {
        this.resourcesType = resourcesType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}