package com.maiacare.serverside.web.controller;

import com.maiacare.serverside.Utils.PageCondition;
import com.maiacare.serverside.web.entity.DrDepartment;
import com.maiacare.serverside.web.response.ServerResponse;
import com.maiacare.serverside.web.service.IdrDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dr-department")
public class DrDepartmentController {

    @Autowired
    public IdrDeptService idrDeptService;

    @PostMapping(value="/get")
    public ServerResponse findAll(@RequestBody PageCondition pc)
    {
        Sort sort =  Sort.by(Sort.Order.desc("id"));
        Pageable pg =  PageRequest.of((pc.getCurrentPage()-1),pc.getPageSize()).withSort(sort);
        ServerResponse msg = new ServerResponse();

        List<DrDepartment> drDepartmentList=idrDeptService.findAll(pg).toList();
        if(drDepartmentList.isEmpty())
        {
        msg.setCode(504);
        msg.setMsg("Failure");
        }
        else
        {
        msg.setCode(200);
        msg.setMsg("Success");
        msg.setObj(drDepartmentList);
        msg.setTotalRecords(idrDeptService.findAll(pg).getTotalElements());
        }
        return msg;
    }

    @PostMapping(value = "/insert")
    public ServerResponse insert(@RequestBody DrDepartment drDepartment)
    {
        DrDepartment p= idrDeptService.insert(drDepartment);
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
    public ServerResponse update(@RequestBody DrDepartment drDepartment)
    {
        DrDepartment p= idrDeptService.save(drDepartment);
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
    public ServerResponse delete(@RequestBody DrDepartment drDepartment)
    {
        System.out.println(drDepartment.getId());
        idrDeptService.delete(drDepartment);
        ServerResponse msg = new ServerResponse();
        msg.setCode(200);
        msg.setMsg("Success");
        return msg;
    }
}
