package com.springboot.main.myproj;

	

	import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
	import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.main.myproj.service.UserService;




	@Configuration
	public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserService userService;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth)throws Exception{
			System.out.println("configure...called");
			auth.authenticationProvider(getProvider());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
			.antMatchers("/executive/add","/busOperator/add/{eid}","/customer/add"
				,"/seat/add/{bid}","/bus/get/filter","/bus/get/{source}/{destination}/{date}"
				,"/bus/get/{bid}","/bus/update/{bid}", "/bus/get-all","/bus/getbysdd/hi","/bus/getwithseatType"
				,"/bus/getwithbusseatType","/bus/getwithbusType","/user/login","/busSchedule/get-all",
				"/busSchedule/get-all","/customer/get/{uid}","/auth/login","/seat/get-all/{bid}",
				"/seat/getavailable/{bid}","/customerBus/booking/multiple/{cid}/{bid}","/executive/get/busOperator",
				"/busOperator/get/bus/{boid}","/busOperator/update/{boid}","/executive/get/customer",
				"/customerBus/get/book/{cid}","/busOperator/bus/delete/{bid}","/bus/add/{boid}").permitAll()
			
			.antMatchers(HttpMethod.POST,"/user/login").authenticated()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and()
			.csrf().disable()
			.cors().disable();
		}
		
		@Bean
		public PasswordEncoder getEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		public Logger getLogger() {
			return LoggerFactory.getLogger("Log Records");
		}
		
		public DaoAuthenticationProvider getProvider() {
			DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
			dao.setPasswordEncoder(getEncoder());
			dao.setUserDetailsService(userService);
			
			return dao;
		}
	}
