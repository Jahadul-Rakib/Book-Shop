package com.book.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.book.impl.SecurityUtility;
import com.book.impl.UserSecurityService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserSecurityService userSecurityService;
	@Autowired
	private SuccessHandler successHandler;

	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/DataTables/**",
			"/image/**",
			"/fonts/**",
			"/admin/css/**",
			"/admin/js/**",
			"/admin/img/**",
			"/admin/assets/**",
			"/admin/contactform/**",
			"/admin/fonts/**",
			"/",
			"/**",
			"/my-account",
			"/newUser",
			"/forgetPassword",
			"/allbookList",
			"/myProfile",
			"/getDetails",
			"/login",
			"/removeUserShipping",
			"/setDefaultShippingAddress",
			"/updateUserShipping",
			"/setDefaultPayment",
			"/removeCreditCard",
			"/updateCreditCard",
			"/addNewShippingAddress",
			"/addNewCreditCard",
			"/listOfShippingAddresses",
			"/listOfCreditCards",


	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login-processing")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(successHandler)
            .failureUrl("/login?error")
            .permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
			.and()
			.rememberMe();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
}
