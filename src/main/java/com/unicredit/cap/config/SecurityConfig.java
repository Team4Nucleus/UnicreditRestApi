package com.unicredit.cap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().ignoringAntMatchers("/token");

  	
        http.cors().and().authorizeRequests()
                .antMatchers("/token").permitAll()
                .antMatchers("/rest/**").authenticated()
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
               
                
                /* .and()
                .headers()
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET, PUT"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));
      */
        
    }

/*
    @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS","PUT","DELETE"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/rest/**", configuration);
		return source;
	}
 
*/
}
