package com.smart_contact_management.smart_contact_management.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig{

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider(){
    //     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //     daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
    //     daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    // return daoAuthenticationProvider;
    // }


    	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

        @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .formLogin(form -> form.loginPage("/signin").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/index"))
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/user/**").hasRole("USER")
        .requestMatchers("/**").permitAll()
        .anyRequest()
        .authenticated());
        return http.build();
    }


    
}
