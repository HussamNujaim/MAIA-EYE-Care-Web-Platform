package com.maiacare.serverside.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "sys_appointment")
public class SysAppointment {
    @Id
    private String id;
    private LocalDateTime appointmentTime;
    private Doctor doctor;
    private String notes;
    private String tag;

    public SysAppointment()
    {
        super();
    }
    public SysAppointment(String id, Doctor doctor, LocalDateTime appointmentTime, String notes, String tag )
    {
        super();
        this.id=id;
        this.appointmentTime=appointmentTime;
        this.doctor=doctor;
        this.notes=notes;
        this.tag=tag;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
