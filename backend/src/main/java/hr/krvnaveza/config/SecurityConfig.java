package hr.krvnaveza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> auth.requestMatchers(
                HttpMethod.GET,
                "/api/doze",
                "/api/doze/*",
                "/api/rezervacije",
                "/api/rezervacije/*",
                "/api/centri",
                "/api/centri/*"
        ).permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}
