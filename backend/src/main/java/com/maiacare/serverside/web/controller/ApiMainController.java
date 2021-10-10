package com.maiacare.serverside.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * this controller used to check anyAPI without login
 * @author Husam Nujaim, Tewelle, Anastasiia
 */

@RestController
@RequestMapping("api")
public class ApiMainController {

    /**
     *
     * @return the current connection statues either
     * the user is anonymousUser or AuthenticatedUser
     */
    @GetMapping("isAuth")
    public ResponseEntity<Map<String, Object>> isAuth(){
        Map<String, Object> result = new HashMap<>();
        result.put("isAuth", !SecurityContextHolder.getContext().
                getAuthentication().getName().
                equals("anonymousUser"));
        return ResponseEntity.ok(result);
    }

    /**
     *
     * @return the User Information
     */
    @GetMapping("/getuserinfo")
    public ResponseEntity<usrRols> UserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        usrRols uRols = new usrRols();
        uRols.setRoles(authentication.getAuthorities());
        uRols.setIntroduction("Our System Is Working");
        uRols.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        uRols.setName(authentication.getName());
        return ResponseEntity.ok(uRols.getRoles().contains("ROLE_ANONYMOUS")?null:uRols);
    }
}


class usrRols{
    Set<String> roles;
    String introduction;
    String avatar;
    String name;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority>  roles) {
        this.roles = AuthorityUtils.authorityListToSet(roles);
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "usrRols{" +
                "roles=" + roles +
                ", introduction='" + introduction + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}