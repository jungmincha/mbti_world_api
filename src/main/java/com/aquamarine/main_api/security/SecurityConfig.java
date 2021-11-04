package com.aquamarine.main_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    

    // @Autowired
    // public void configGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication().withUser("aquamarine").password("$2a$12$DhMcs/oYSApH/KB7lcl7Uet3Vi2hlj5QfsSWnF2HFy6JV3VcEpKti").roles("API");
    //     System.out.println(auth);
    // }

    @Override
    public void configure(WebSecurity web) throws Exception {

   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable()
        //         .cors()
        //         .and()
        //         .httpBasic()
        http.cors() //크로스오리진 설정을 해준다.
            .and()
            .csrf().disable()//csrf 공격 방어
            .authorizeRequests() //서버에 접근할 권한을 설정해준다.
            .mvcMatchers("/api/**").permitAll()
            .anyRequest().authenticated()
        ;
    }

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowedOrigins(Arrays.asList("*"));
    //     configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    //     configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
    //     configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
