package com.maiacare.serverside.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/* <beans>   </beans> */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${filePath}")
    String realPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+realPath+"/");
    }
}
