package com.maiacare.serverside.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sys_role")
public class SysRole {
    @Id
    private String id;
    private String roleName;

    public SysRole()
    {
        super();
    }
    public SysRole( String id, String roleName)
    {
        super();
        this.id=id;
        this.roleName=roleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
