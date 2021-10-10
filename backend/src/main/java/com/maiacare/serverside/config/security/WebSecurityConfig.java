package com.maiacare.serverside.config.security;

import com.maiacare.serverside.web.service.IsysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/about", "/landing", "/landing/index", "/","/favicon.ico","/static/**","/login", "/api/getuserinfo","/api/isAuth","/api/sys-user/register","/api/sys-user/uploadPicture").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
//                        测试用
                        "/*.jpg",
                        "/image/*.jpg",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
//                        ,"/**/*.jpg"
                ).permitAll()
                .anyRequest().authenticated()
                .and()

                .exceptionHandling().authenticationEntryPoint(new com.maiacare.serverside.config.security.CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new com.maiacare.serverside.config.security.CustomAccessDeniedHandler())
                .and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, auth) -> {
                    resp.getWriter().print(auth.getName());
                })
                .and()
                .csrf().disable();
        http.addFilterAt(customJSONLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    IsysUserService isysUserService;



    public com.maiacare.serverside.config.security.CustomJSONLoginFilter customJSONLoginFilter(){

        com.maiacare.serverside.config.security.CustomJSONLoginFilter customJSONLoginFilter= new com.maiacare.serverside.config.security.CustomJSONLoginFilter("/authentication",isysUserService);
        // verify username password right  will return userinfo to front-end
        customJSONLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessfulHandler(isysUserService));
        customJSONLoginFilter.setAuthenticationFailureHandler(new com.maiacare.serverside.config.security.CustomAuthenticationFailureHandler());
        return customJSONLoginFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        // don't use encryption algorithm
//        return NoOpPasswordEncoder.getInstance();
        String encoderType= com.maiacare.serverside.config.security.EncryptionAlgorithm.ENCODER_TYPE.get(1);
        PasswordEncoder passwordEncoder=  com.maiacare.serverside.config.security.EncryptionAlgorithm.ENCODER_MAP.get(encoderType);

        return passwordEncoder;
    }



}
