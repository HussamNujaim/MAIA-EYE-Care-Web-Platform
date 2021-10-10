package com.maiacare.serverside.config.security;

import com.alibaba.fastjson.JSON;
import com.maiacare.serverside.web.entity.SysUser;
import com.maiacare.serverside.web.response.ServerResponse;
import com.maiacare.serverside.web.service.IsysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class CustomAuthenticationSuccessfulHandler implements AuthenticationSuccessHandler {

    public CustomAuthenticationSuccessfulHandler(IsysUserService isysUserService){
        this.isysUserService=isysUserService;
    }

    IsysUserService isysUserService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SysUser e=new SysUser();
        e.setUsername(authentication.getPrincipal().toString());
        Example<SysUser> ex= Example.of(e);
        SysUser user = isysUserService.findOne(ex).get();


        System.out.println("*authentication: "+authentication.getPrincipal().toString());
        System.out.println("user: "+user.getUsername());

        System.out.println("**************login verify ok authentication:"+authentication);
        System.out.println("Credentials: "+authentication.getCredentials());
        System.out.println("Authorities: "+authentication.getAuthorities());

        ServerResponse msg=new ServerResponse();
        msg.setCode(200);
        msg.setToken(authentication.getAuthorities().toString());
        msg.setMsg("login successful");
        msg.setObj(user);

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(msg));


    }
}
