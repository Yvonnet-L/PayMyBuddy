package com.oc.ly.PayMyBuddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);


        PasswordEncoder passwordEncoder = passwordEncoder();
        System.out.println("*************************");
        System.out.println(passwordEncoder.encode("123"));
        System.out.println("*************************");
        System.out.println(userDetailsService.getClass().getName());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/save**/**","/delete**/**","/admin").hasRole("ADMIN")
                    .anyRequest().authenticated();

        http.formLogin().loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll();
        http.logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/login");
        http.exceptionHandling().accessDeniedPage("/notAuthorized");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    Important:
    Une fois PasswirdEncoder mis en place, spring security appliquera celui si sur la donneé entrante du mot de passe.
    En gros il va crypter la donnée grace à  BCryptPasswordEncoder(), il faut donc encoder aussi les passwords de la base avec celui ci.
    Dans le code avec un appel @Autowired et pour le remplissage direct il suffit s'uttiliser une simple passwordEncoder.encode() dans syso.
     ex : System.out.println(passwordEncoder.encode("1234"));
    il nous restera juste à récuperer la donnée cryptée dans la console puis de la sauvegarder en base.
     */

}
