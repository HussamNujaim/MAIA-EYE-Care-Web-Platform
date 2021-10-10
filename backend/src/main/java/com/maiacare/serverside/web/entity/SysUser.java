package com.maiacare.serverside.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "sys_user")
public class SysUser {
    @Id
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String imgUrl;
    private List<SysRole> sysRole;
    public SysUser()
    {
        super();
    }
    public SysUser(String id, String username, String password, String nickname, String email, String phone, String imgUrl, List<SysRole> sysRole)
    {
        super();
        this.id=id;
        this.username=username;
        this.password=password;
        this.nickname =nickname;
        this.email=email;
        this.phone=phone;
        this.imgUrl=imgUrl;
        this.sysRole = sysRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<SysRole> getSysRole() {
        return sysRole;
    }

    public void setSysRole(List<SysRole> sysRole) {
        this.sysRole = sysRole;
    }
}
