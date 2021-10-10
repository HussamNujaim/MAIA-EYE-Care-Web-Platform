package com.maiacare.serverside.web.controller;

import com.maiacare.serverside.config.security.EncryptionAlgorithm;
import com.maiacare.serverside.web.entity.Patient;
import com.maiacare.serverside.web.entity.SysUser;
import com.maiacare.serverside.web.response.ServerResponse;
import com.maiacare.serverside.web.service.IpatientsService;
import com.maiacare.serverside.web.service.IsysUserService;
import com.maiacare.serverside.web.service.IuploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/sys-user")
public class SysUserController {

    @Autowired
    public IsysUserService isysUserService;

    @GetMapping(value="/get")
    public List<SysUser> findAll()
    {
       return isysUserService.findAll();
    }

    @PostMapping("getuserinfo")
    public SysUser getuserinfo(@RequestBody SysUser user) {
        Example<SysUser> ex= Example.of(user);
        SysUser sysUser = isysUserService.findOne(ex).get();
        return sysUser;
    }

    @PostMapping(value = "/register")
    public ServerResponse insert(@RequestBody SysUser user){
        ServerResponse msg = new ServerResponse();
        //encrypt the password
        String encorderType= EncryptionAlgorithm.ENCODER_TYPE.get(1);
        PasswordEncoder passwordEncoder =EncryptionAlgorithm.ENCODER_MAP.get(encorderType);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        SysUser u= isysUserService.insert(user);
        if(u!=null)
        {
            msg.setCode(200);
            msg.setMsg("Registration is successful");
            msg.setObj(user);
        }else{
            msg.setCode(504);
            msg.setMsg("Registration failed");
        }

        return msg;
    }

    @Autowired
    IuploadService iuploadService;

    @PostMapping("uploadPicture")
    public ServerResponse uploadPicture(@RequestParam(name = "fileName", required = false) MultipartFile uploadFile, HttpServletRequest request)
    {
        ServerResponse msg=new ServerResponse();
        String imgpath= iuploadService.uploadPicture(uploadFile,request);
        System.out.println("Image Path: "+imgpath);
        msg.setCode(200);
        msg.setMsg("Picture has been uploaded successfully");
        msg.setObj(imgpath);
        return msg;
    }

    @PostMapping(value = "/update")
    public String update(@RequestBody SysUser sysUser)
    {
        SysUser u= isysUserService.findAll().set(Integer.parseInt(sysUser.getId()+""), sysUser);
        return "User: "+ u.getUsername()+" has been edited successfully!";
    }
}
