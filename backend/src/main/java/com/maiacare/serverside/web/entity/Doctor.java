package com.maiacare.serverside.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctor")
public class Doctor {
    @Id
    private String id;
    private String doctorName;
    private int age;
    private String address;
    private String gender;
    private double salary;
    private DrDepartment dept;

    public Doctor()
    {
        super();
    }
    public Doctor(String id, String doctorName, int age, String address, String gender, double salary, DrDepartment dept)
    {
        super();
        this.id=id;
        this.doctorName=doctorName;
        this.age=age;
        this.address=address;
        this.gender=gender;
        this.salary=salary;
        this.dept=dept;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public DrDepartment getDept() {
        return dept;
    }

    public void setDept(DrDepartment dept) {
        this.dept = dept;
    }
}
