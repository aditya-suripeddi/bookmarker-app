package com.dev.bookmarker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // CORS: https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // for calls made to endpoints starting with /api/**
                .allowedMethods("*") // Allow all HTTP Methods
                .allowedHeaders("*")  // Allow all headers
                .allowedOriginPatterns("*"); // if your api allows calls only from a particular domain
                                             // you can mention that in allowed Origin patterns method
    }
}
