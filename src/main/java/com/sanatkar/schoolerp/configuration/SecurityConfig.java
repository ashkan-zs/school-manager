package com.sanatkar.schoolerp.configuration;

import com.sanatkar.schoolerp.model.entity.Privilege;
import com.sanatkar.schoolerp.model.repository.PrivilegeDao;
import com.sanatkar.schoolerp.model.service.UserServiceImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;

/**
 * Create by ashkan on 2019/06/13
 */

@Log4j2
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImp userDetailsService;

    @Autowired
    private PrivilegeDao privilegeDao;

    @Autowired
    public void setUserDetailsService(UserServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder());

        return provider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**"
                        , "/i18n/**"
                        , "/webfonts/**"
                        , "/css/**"
                        , "/images/**"
                        , "/js/**"
                        , "/webjars/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TODO: add privileges programmatically problem with orders
        List<Privilege> privilegeList = privilegeDao.findAll();
        for (Privilege privilege : privilegeList) {
            if (!privilege.getName().isEmpty() && !privilege.getUrl().isEmpty()) {
                String[] urls = privilege.getUrl().split(",");
                String[] authorities = privilege.getName().split(",");
                http.authorizeRequests()
                        .mvcMatchers(urls)
                        .hasAnyAuthority(authorities);
            }
        }

        // @formatter:off
        http
            .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true)
            .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .accessDeniedHandler(accessDeniedHandler());
        // @formatter:on
    }
}
