package com.entity.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles extends BaseModel implements Serializable {
    @Column(name="id")
    @Id
    private Integer id;

    @Column(name="role_name")
    private String roleName;

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
}