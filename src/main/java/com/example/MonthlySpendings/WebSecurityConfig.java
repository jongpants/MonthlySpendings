package com.example.MonthlySpendings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.MonthlySpendings.web.UserDetailServiceImpl;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/spendinglist", "/add", "/delete/**", "/edit/**").permitAll()
          
 //			The code below is the EXACT SAME as the ones provided in the materials but it does not work..
 //			It will restrict those URLs from all users.
 //       .antMatchers("/spendings/**", "/users/**", "/frequencies/**", "/types/**", "/profile/**").hasRole("ADMIN")
          .anyRequest().authenticated()
          .and()
        .formLogin()
          .defaultSuccessUrl("/spendinglist", true)
          .permitAll()
          .and()
        .logout()
          .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
