package com.maiacare.serverside.web.controller;

import com.maiacare.serverside.web.entity.Patient;
import com.maiacare.serverside.web.service.IpatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/patient")
public class PatientsController {

    @Autowired
    public IpatientsService patientsService;

    @GetMapping(value="/get")
    public List<Patient> findAll()
    {
       return patientsService.findAll();
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody Patient newPatient)
    {
        Patient p= patientsService.insert(newPatient);
        return "Patient: "+ p.getPatientName()+" has been added successfully!";
    }

    @PostMapping(value = "/update")
    public String update(@RequestBody Patient newPatient)
    {
        Patient p= patientsService.findAll().set(Integer.parseInt(newPatient.getId()+""),newPatient);
        return "Patient: "+ p.getPatientName()+" has been edited successfully!";
    }
}
