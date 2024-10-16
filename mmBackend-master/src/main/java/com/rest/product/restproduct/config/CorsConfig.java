package com.rest.product.restproduct.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173,localhost:5174,localhost:5175,masterpiecemart.vercel.app,https://masterpiecemart.vercel.app,https://masterpiecemart.vercel.app/") 
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
