package com.poly;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.poly.entity.Account;
import com.poly.service.AccountService;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	AccountService asv;
	
	//Encode password
	@Bean
	public BCryptPasswordEncoder getPassEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Authentication Manager
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception   {
		auth.userDetailsService( username -> {
			try {
				Account acc = asv.findById(username);
				
				//create user detail
				String password = acc.getPassword();
				String[] roles = acc.getAuthorities().stream()
						.map(au -> au.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				
				return User.withUsername(username)
						.password(pe.encode(password))
						.roles(roles).build();
			} catch (UsernameNotFoundException e) {
				throw new UsernameNotFoundException(username+" not found");
			}
		});
	}
	
	
	//Authorization and login method interface
	@Override
	protected void configure(HttpSecurity http) throws Exception  {
		//CSRF,CORS
		http.csrf().disable().cors().disable();
		
		//authorization
		http.authorizeRequests()
			.antMatchers("/order/**").authenticated()
			.antMatchers("/admin/**").hasAnyRole("STA","ADM")
			.antMatchers("/admins/**").hasAnyRole("STA","ADM")
			.antMatchers("/rest/authorities").hasRole("ADM")
			.anyRequest().permitAll();   
		
			
		
		//access dinied router
		http.exceptionHandling()
			.accessDeniedPage("/auth/access/denied");
		
		
		//login method interface
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/security/login/success",false)
			.failureUrl("/security/login/error")
			.usernameParameter("username")
			.passwordParameter("password");
			
		http.rememberMe()
			.tokenValiditySeconds(86400);
		
		//logout
		http.logout()
			.logoutUrl("/security/logoff")
			.logoutSuccessUrl("/security/logoff/success");
				
		
	}
	
	
}
