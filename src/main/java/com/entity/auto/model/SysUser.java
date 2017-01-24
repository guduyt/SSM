package com.entity.auto.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sys_user")
public class SysUser extends BaseModel implements Serializable {
    /**
    * 用户主键
    */
    @Column(name="id")
    @Id
    private Long id;

    /**
    * 用户名
    */
    @Column(name="user_name")
    private String userName;

    /**
    * 密码
    */
    @Column(name="password")
    private String password;

    /**
    * 手机号
    */
    @Column(name="mobile")
    private String mobile;

    /**
    * 邮箱
    */
    @Column(name="email")
    private String email;

    /**
    * 启用标志
    */
    @Column(name="enable")
    private Boolean enable;

    /**
    * 锁定标志
    */
    @Column(name="is_lock")
    private Boolean isLock;

    /**
    * 过期时间
    */
    @Column(name="expire")
    private Date expire;

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