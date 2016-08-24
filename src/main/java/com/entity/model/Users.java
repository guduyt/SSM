package com.entity.model;

import com.yt.mybatis.model.BaseModel;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users extends BaseModel implements Serializable {
    @Column(name="id")
    @Id
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private Integer age;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}