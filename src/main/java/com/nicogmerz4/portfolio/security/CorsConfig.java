package com.nicogmerz4.portfolio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigImpl() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/login")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .exposedHeaders("*");
                
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
                
            }  
        };
    }
}
