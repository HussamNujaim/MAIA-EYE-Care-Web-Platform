package com.maiacare.serverside.web.controller;

import com.maiacare.serverside.web.entity.Appointment;
import com.maiacare.serverside.web.service.IappointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

    @Autowired
    public IappointmentService iappointmentService;

    @GetMapping(value="/get")
    public List<Appointment> findAll()
    {
       return iappointmentService.findAll();
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody Appointment appointment)
    {
        Appointment p= iappointmentService.insert(appointment);
        return "Appointment: "+ p.getAppointmentTime()+" has been added successfully!";
    }

    @PostMapping(value = "/update")
    public String update(@RequestBody Appointment appointment)
    {
        Appointment p= iappointmentService.findAll().set(Integer.parseInt(appointment.getId()+""),appointment);
        return "Appointment: "+ p.getAppointmentTime()+" has been edited successfully!";
    }
}
