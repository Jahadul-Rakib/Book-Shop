package com.book.config.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebMvcConfiguration implements WebMvcConfigurer{

    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("admin/book/**").
                addResourceLocations("/");
    }
}
