package com.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // autorise toutes les routes
                        .allowedOrigins("https://cira-cf6b0.web.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // autorise les méthodes HTTP
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
