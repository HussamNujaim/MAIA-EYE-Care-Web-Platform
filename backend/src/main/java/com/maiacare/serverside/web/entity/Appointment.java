package com.maiacare.serverside.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "appointment")
public class Appointment {
    @Id
    private String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentTime;
    private String diagnosis;
    private String notes;

    public Appointment()
    {
        super();
    }
    public Appointment(String id, Patient patient, Doctor doctor, LocalDateTime appointmentTime, String diagnosis, String notes )
    {
        super();
        this.id=id;
        this.appointmentTime=appointmentTime;
        this.diagnosis=diagnosis;
        this.doctor=doctor;
        this.patient=patient;
        this.notes=notes;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
