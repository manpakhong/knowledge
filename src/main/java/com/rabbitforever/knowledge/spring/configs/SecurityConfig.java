package com.rabbitforever.knowledge.spring.configs;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

import com.rabbitforever.knowledge.security.AuthenticationSuccessHandlerImpl;
import com.rabbitforever.knowledge.services.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private AuthenticationSuccessHandlerImpl successHandler;
    @Autowired
    private DataSource dataSource;
    private UserService userService;
	@Override
	public void configure(AuthenticationManagerBuilder auth)
			throws Exception {

		// in-memory authentication
//		 auth.inMemoryAuthentication()
//		 .withUser("dave").password(passwordEncoder.encode("password")).roles("USER")
//	     .and()
//	     .withUser("admin").password(passwordEncoder.encode("password")).roles("USER", "ADMIN");

		// using custom UserDetailsService DAO
		// auth.userDetailsService(new AppUserDetailsServiceDAO());

		// using JDBC
//		Context ctx = new InitialContext();
//		DataSource ds = (DataSource) ctx
//				.lookup("java:/comp/env/jdbc/MyLocalDB");
//
//		final String findUserQuery = "select username,password,enabled "
//				+ "from Employees " + "where username = ?";
//		final String findRoles = "select username,role " + "from Roles "
//				+ "where username = ?";
//		
//		auth.jdbcAuthentication().dataSource(ds)
//				.usersByUsernameQuery(findUserQuery)
//				.authoritiesByUsernameQuery(findRoles);
		
        auth.userDetailsService(userService)
        .passwordEncoder(passwordEncoder)
        .and()
        .authenticationProvider(authenticationProvider())
        .jdbcAuthentication()
        .dataSource(dataSource);
	}
	
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    
    @PostConstruct
    public void completeSetup() {
        userService = applicationContext.getBean(UserService.class);
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//            .ignoring()
//            .antMatchers("/resources/**");
//    }
//	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
      http.authorizeRequests()
//      .antMatchers("**")
//          .permitAll()
      .antMatchers("/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
//          .hasAnyRole("ROLE_ADMIN", "ROLE_USER")
      .and()
          .formLogin()
          .loginPage("/login")
          .successHandler(successHandler)
          .defaultSuccessUrl("/web/admin")                
          
          .failureUrl("/login?error=true")
          
          .permitAll()
      .and()
          .logout()
          .logoutSuccessUrl("/login?logout=true")
          .invalidateHttpSession(true)
          .permitAll()
      .and()
          .csrf()
          .disable();
  
    	

//	    http.authorizeRequests()
//		.antMatchers("**").access("hasRole('ROLE_USER')")
//		.and()
//		    .formLogin().loginPage("/login").failureUrl("/login?error")
//		    .usernameParameter("username").passwordParameter("password")		
//		.and()
//		    .logout().logoutSuccessUrl("/login?logout")
//		.and()
//		    .csrf(); 
      
//	    http.authorizeRequests().antMatchers("/login")
//        .permitAll()
//		.antMatchers("/**").access("hasRole('ROLE_USER')")
//		.and()
//		    .formLogin().loginPage("/login").failureUrl("/login?error")
//		    .usernameParameter("username").passwordParameter("password")		
//		.and()
//		    .logout().logoutSuccessUrl("/login?logout")
//		.and()
//		    .csrf().and().anonymous().disable(); 
    	
	    
//        http.authorizeRequests()
//        .antMatchers("/login")
//            .permitAll()
//        .antMatchers("/**")
//            .hasAnyRole("ADMIN", "USER")
//        .and()
//            .formLogin()
//            .loginPage("/login")
//            .defaultSuccessUrl("/home")
//            .failureUrl("/login?error=true")
//            .permitAll()
//        .and()
//            .logout()
//            .logoutSuccessUrl("/login?logout=true")
//            .invalidateHttpSession(true)
//            .permitAll()
//        .and()
//            .csrf()
//            .disable();
//        http.authorizeRequests()
//        .antMatchers("/login")
//        .permitAll()
//        .and()
//        .formLogin()
//        .permitAll()
//        .successHandler(successHandler)
//        .and()
//        .csrf()
//        .disable();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    
//    @Bean
//    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//        return new SecurityEvaluationContextExtension();
//    }
}
