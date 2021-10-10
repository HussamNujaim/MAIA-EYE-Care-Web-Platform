package com.maiacare.serverside.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dr_department")
public class DrDepartment {
    @Id
    private String id;
    private String deptName;
    private String notes;

    public DrDepartment()
    {
        super();
    }
    public DrDepartment(String id, String deptName, String notes)
    {
        super();
        this.id=id;
        this.deptName=deptName;
        this.notes=notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
