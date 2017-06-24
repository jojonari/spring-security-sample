package com.fast87.study.config;

import com.fast87.study.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        //http.cors().and().addFilter(null).authorizeRequests().anyRequest();
        http
            .authorizeRequests()
            .anyRequest()
            .permitAll()            // 모든 요청에 대해서 접근을 허용
            .and()
            .formLogin()
            .and()
                .addFilterAfter(simpleFilter(), UsernamePasswordAuthenticationFilter.class)
            .userDetailsService(userService);
    }

    public Filter simpleFilter(){
        return new OncePerRequestFilter(){
            @Override
            protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
                System.out.println("여기가 호출됩니다");

            }
        };
    }
}