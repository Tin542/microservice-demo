package com.tinnt.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:4200"); // Cho phép Angular frontend
        corsConfig.addAllowedMethod("*"); // GET, POST, PUT, DELETE, etc
        corsConfig.addAllowedHeader("*"); // Cho tất cả headers
        corsConfig.setAllowCredentials(true); // Nếu cần gửi cookies, Authorization Header

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Áp dụng cho tất cả URL

        return new CorsWebFilter(source);
    }
}
