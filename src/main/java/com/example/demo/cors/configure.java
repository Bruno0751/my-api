package com.example.demo.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class configure implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        System.out.println("------------------------------------------------------------------\n" +
                "INTO webMvcConfigure\n" +
                "------------------------------------------------------------------");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
