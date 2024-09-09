package com.master.app.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.GET, "/user").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                .anyRequest().authenticated())
                // Aquí se deshabilita una configuracion de seguridad que tiene spring boot por defecto
                .csrf(AbstractHttpConfigurer::disable)
                // Aquí se configura para que no se almacenen las sesiones. Es comun en aplicaciones API rest ful
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
