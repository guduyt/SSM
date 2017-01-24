package com.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sys_resource")
public class SysResource extends BaseModel implements Serializable {
    /**
    * 资源主键
    */
    @Column(name="id")
    @Id
    private Integer id;

    /**
    * 资源名
    */
    @Column(name="resources_name")
    private String resourcesName;

    /**
    * 资源类型
    */
    @Column(name="resources_type")
    private Byte resourcesType;

    /**
    * 资源
    */
    @Column(name="resource_path")
    private String resourcePath;

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

    /**
    * 父级资源
    */
    @Column(name="parent_id")
    private Integer parentId;

    /**
    * 创建人
    */
    @Column(name="creator")
    private String creator;

    /**
    * 创建时间
    */
    @Column(name="create_time")
    private Date createTime;

    /**
    * 修改人
    */
    @Column(name="editor")
    private String editor;

    /**
    * 修改时间
    */
    @Column(name="edit_time")
    private Date editTime;

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

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName == null ? null : resourcesName.trim();
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
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}