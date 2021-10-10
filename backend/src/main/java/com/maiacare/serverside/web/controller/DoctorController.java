package com.maiacare.serverside.web.controller;

import com.maiacare.serverside.Utils.PageCondition;
import com.maiacare.serverside.web.entity.Doctor;
import com.maiacare.serverside.web.entity.DrDepartment;
import com.maiacare.serverside.web.response.ServerResponse;
import com.maiacare.serverside.web.service.IdoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping(value = "/api/doctor")
public class DoctorController {

    @Autowired
    public IdoctorService idoctorService;

    @PostMapping(value="/get")
    public ServerResponse findAll(@RequestBody PageCondition pc)
    {
        Sort sort =  Sort.by(Sort.Order.desc("id"));
        Pageable pg =  PageRequest.of((pc.getCurrentPage()-1),pc.getPageSize()).withSort(sort);
        ServerResponse msg = new ServerResponse();

        List<Doctor> doctor=idoctorService.findAll(pg).toList();
        if(doctor.isEmpty())
        {
            msg.setCode(504);
            msg.setMsg("Failure");
        }
        else
        {
            msg.setCode(200);
            msg.setMsg("Success");
            msg.setObj(doctor);
            msg.setTotalRecords(idoctorService.findAll(pg).getTotalElements());
        }
        return msg;
    }

    @PostMapping(value = "/insert")
    public ServerResponse insert(@RequestBody Doctor doctor)
    {
        System.out.println(doctor.getDoctorName());
        System.out.println(doctor.getDept());
        Doctor p= idoctorService.insert(doctor);
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
    public ServerResponse update(@RequestBody Doctor doctor)
    {
        Doctor p= idoctorService.save(doctor);
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
    public ServerResponse delete(@RequestBody Doctor doctor)
    {
        System.out.println(doctor.getId());
        idoctorService.delete(doctor);
        ServerResponse msg = new ServerResponse();
        msg.setCode(200);
        msg.setMsg("Success");
        return msg;
    }
}
