package com.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

/**
* 数据表:sys_user
* 用户表
*/
public class SysUser extends BaseModel implements Serializable {
    /**
    * 字段:sys_user.id
    * 用户主键
    */
    private Long id;

    /**
    * 字段:sys_user.user_name
    * 用户名
    */
    private String userName;

    /**
    * 字段:sys_user.password
    * 密码
    */
    private String password;

    /**
    * 字段:sys_user.mobile
    * 手机号
    */
    private String mobile;

    /**
    * 字段:sys_user.email
    * 邮箱
    */
    private String email;

    /**
    * 字段:sys_user.enable
    * 启用标志 0禁用1启用
    */
    private Boolean enable;

    /**
    * 字段:sys_user.is_lock
    * 锁定标志 0锁定1启用
    */
    private Boolean isLock;

    /**
    * 字段:sys_user.expire
    * 过期时间
    */
    private Date expire;

    /**
    * 字段:sys_user.remark
    * 备注
    */
    private String remark;

    /**
    * 字段:sys_user.creator
    * 创建人
    */
    private String creator;

    /**
    * 字段:sys_user.create_time
    * 创建时间
    */
    private Date createTime;

    /**
    * 字段:sys_user.editor
    * 修改人
    */
    private String editor;

    /**
    * 字段:sys_user.edit_time
    * 修改时间
    */
    private Date editTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
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