package com.maiacare.serverside.web.controller;

import com.maiacare.serverside.Utils.PageCondition;
import com.maiacare.serverside.web.entity.Appointment;
import com.maiacare.serverside.web.entity.Doctor;
import com.maiacare.serverside.web.entity.SysAppointment;
import com.maiacare.serverside.web.response.ServerResponse;
import com.maiacare.serverside.web.service.IappointmentService;
import com.maiacare.serverside.web.service.IsysAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sys-appointment")
public class SysAppointmentController {

    @Autowired
    public IsysAppointmentService isysAppointmentService;

    @PostMapping(value="/get")
    public ServerResponse findAll(@RequestBody PageCondition pc)
    {
        Sort sort =  Sort.by(Sort.Order.desc("id"));
        Pageable pg =  PageRequest.of((pc.getCurrentPage()-1),pc.getPageSize()).withSort(sort);
        ServerResponse msg = new ServerResponse();

        List<SysAppointment> appointment=isysAppointmentService.findAll(pg).toList();
        if(appointment.isEmpty())
        {
            msg.setCode(504);
            msg.setMsg("Failure");
        }
        else
        {
            msg.setCode(200);
            msg.setMsg("Success");
            msg.setObj(appointment);
            msg.setTotalRecords(isysAppointmentService.findAll(pg).getTotalElements());
        }
        return msg;
    }

    @PostMapping(value = "/insert")
    public ServerResponse insert(@RequestBody SysAppointment appointment)
    {
        SysAppointment p= isysAppointmentService.insert(appointment);
        ServerResponse msg = new ServerResponse();
        if(p!=null)
        {
            msg.setCode(504);
            msg.setMsg("Failure");
        }
        else
        {
            msg.setCode(200);
            msg.setMsg("Success");
            msg.setObj(p);
        }
        return msg;
    }


    @PostMapping(value = "/update")
    public ServerResponse update(@RequestBody SysAppointment appointment)
    {
        SysAppointment p= isysAppointmentService.save(appointment);
        ServerResponse msg = new ServerResponse();
        if(p!=null)
        {
            msg.setCode(504);
            msg.setMsg("Failure");
        }
        else
        {
            msg.setCode(200);
            msg.setMsg("Success");
            msg.setObj(p);
        }
        return msg;
    }

    @PostMapping(value = "/delete")
    public ServerResponse delete(@RequestBody SysAppointment appointment)
    {
        System.out.println(appointment.getId());
        isysAppointmentService.delete(appointment);
        ServerResponse msg = new ServerResponse();
        msg.setCode(200);
        msg.setMsg("Success");
        return msg;
    }
}
