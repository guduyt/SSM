package com.entity.manual.model;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by yt on 2016-10-14.
 */
public class User implements UserDetails,CredentialsContainer {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private Long id;

    private String userName;

    private String password;

    private String mobile;

    private String email;

    private Boolean enable;

    private Boolean isLock;

    private Date expire;

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
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return this.userName;
    }

    private List<Role>  listRole;

    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }

    public List<Role> getListRole() {
        return listRole;
    }

    public void setListRole(List<Role> listRole) {
        this.listRole = listRole;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return listRole;
    }


    public boolean isAccountNonExpired() {
        if(expire==null)
            return true;
        return expire.compareTo(new Date())>0?true:false;
    }


    public boolean isAccountNonLocked() {
        return this.isLock;
    }


    public boolean isCredentialsNonExpired() {
        if(expire==null)
            return true;
        return expire.compareTo(new Date())>0?true:false;
    }


    public boolean isEnabled() {
        return this.enable;
    }




    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof User) {
            return userName.equals(((User) rhs).userName);
        }
        return false;
    }


    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("UserName: ").append(this.userName).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Mobile: ").append(this.mobile).append("; ");
        sb.append("Email: ").append(this.email).append("; ");
        sb.append("Enable: ").append(this.enable).append("; ");
        sb.append("IsLock: ").append(this.isLock).append("; ");
        sb.append("Expire: ").append(this.expire).append("; ");

        if (!listRole.isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : listRole) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        }
        else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }
}