package my.ah.school_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Allow preflight requests
        http.cors().configurationSource(corsConfigurationSource());

        // fix 'Invalid CSRF token found for' xxx
        http.csrf().disable();

        http.authorizeRequests().anyRequest().permitAll();

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        List<String> allowedOrigins = Arrays.asList("http://localhost:4200", "http://localhost:4300");
        List<String> allowedMethods = Arrays.asList("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);  //todo: externalized cors
        configuration.setAllowedMethods(allowedMethods); //todo: externalized methods
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
