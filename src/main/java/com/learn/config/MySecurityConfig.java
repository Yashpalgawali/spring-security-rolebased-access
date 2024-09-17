package com.learn.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.learn.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	private CustomUserDetailService customUserDetailService;  
	
	
	public MySecurityConfig(CustomUserDetailService customUserDetailService) {
		super();
		this.customUserDetailService = customUserDetailService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.authorizeRequests()
			.antMatchers("/signin").permitAll()
			.antMatchers("/public/**").hasAnyRole("NORMAL","ADMIN")
			.antMatchers("/users/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/signin")
			.loginProcessingUrl("/dologin")
			.defaultSuccessUrl("/users/")
			
			.and()
			.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
			;
	}
	
//	private DataSource dataSource;
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
		
//		auth.inMemoryAuthentication().withUser("john").password(this.passwordEncoder().encode("durgesh")).roles("NORMAL");
//		auth.inMemoryAuthentication().withUser("yash").password(this.passwordEncoder().encode("yash")).roles("ADMIN");
		 
	}
	

	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder(10);
	}
} 
