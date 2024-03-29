package com.proyecto.ClinicaOdontologica.service.securityConf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRolesService userRolesService;

    @Autowired
    private BCryptPasswordEncoder BCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            /*http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/odontologos/**").permitAll()
                    .antMatchers("/pacientes/**").permitAll()
                    .antMatchers("/turnos/**").permitAll()
                    .and()
                    .formLogin().permitAll();*/

        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(BCryptPasswordEncoder);
        authenticationProvider.setUserDetailsService(userRolesService);
        auth.authenticationProvider(authenticationProvider);
    }

}
