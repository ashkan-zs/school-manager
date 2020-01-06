package com.sanatkar.schoolerp.configuration;

import com.sanatkar.schoolerp.model.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Create by ashkan on 2019/06/13
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImp userDetailsService;

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
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http
            .authorizeRequests()
                // Access Privileges
                .mvcMatchers("/users", "/users/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_USER")
                .mvcMatchers("/years", "/years/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_YEAR")
                .mvcMatchers("/classes", "/classes/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_CLASS")
                .mvcMatchers("/courses", "/courses/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_COURSE")
                .mvcMatchers("/levels", "/levels/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_LEVEL")
                .mvcMatchers("/employees", "/employees/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_EMPLOYEE")
                .mvcMatchers("/guardians", "/guardians/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_GUARDIAN")
                .mvcMatchers("/jobs", "/jobs/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_JOB")
                .mvcMatchers("/roles", "/roles/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_ROLE")
                .mvcMatchers("/schools", "/schools/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_SCHOOL")
                .mvcMatchers("/students", "/students/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_STUDENT")
                .mvcMatchers("/transcripts", "/transcripts/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_TRANSCRIPT")
                .mvcMatchers("/absences", "/absences/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_ROLLCALL")
                .mvcMatchers("/studentGuardians", "/studentGuardians/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_STUDENT_GUARDIAN")
                .mvcMatchers("/studentLeaves", "/studentLeaves/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_LEAVE")
                .mvcMatchers("/courseLevels", "/courseLevels/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_COURSE_LEVEL")
                .mvcMatchers("/teachers", "/teachers/*").hasAnyAuthority("ALL_PRIVILEGE", "SHOW_TEACHER")
                // Add Privileges
                .mvcMatchers("/users/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_USER")
                .mvcMatchers("/years/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_YEAR")
                .mvcMatchers("/classes/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_CLASS")
                .mvcMatchers("/courses/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_COURSE")
                .mvcMatchers("/levels/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_LEVEL")
                .mvcMatchers("/employees/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_EMPLOYEE")
                .mvcMatchers("/guardians/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_GUARDIAN")
                .mvcMatchers("/jobs/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_JOB")
                .mvcMatchers("/roles/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_ROLE")
                .mvcMatchers("/schools/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_SCHOOL")
                .mvcMatchers("/students/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_STUDENT")
                .mvcMatchers("/transcripts/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_TRANSCRIPT")
                .mvcMatchers("/absences/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_ROLLCALL")
                .mvcMatchers("/studentGuardians/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_STUDENT_GUARDIAN")
                .mvcMatchers("/studentLeaves/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_LEAVE")
                .mvcMatchers("/courseLevels/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_COURSE_LEVEL")
                .mvcMatchers("/teachers/add").hasAnyAuthority("ALL_PRIVILEGE", "ADD_TEACHER")
                // Edit Privileges
                .mvcMatchers("/users/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_USER")
                .mvcMatchers("/years/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_YEAR")
                .mvcMatchers("/classes/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_CLASS")
                .mvcMatchers("/courses/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_COURSE")
                .mvcMatchers("/levels/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_LEVEL")
                .mvcMatchers("/employees/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_EMPLOYEE")
                .mvcMatchers("/guardians/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_GUARDIAN")
                .mvcMatchers("/jobs/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_JOB")
                .mvcMatchers("/roles/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_ROLE")
                .mvcMatchers("/schools/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_SCHOOL")
                .mvcMatchers("/students/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_STUDENT")
                .mvcMatchers("/transcripts/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_TRANSCRIPT")
                .mvcMatchers("/absences/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_ROLLCALL")
                .mvcMatchers("/studentGuardians/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_STUDENT_GUARDIAN")
                .mvcMatchers("/studentLeaves/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_LEAVE")
                .mvcMatchers("/courseLevels/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_COURSE_LEVEL")
                .mvcMatchers("/teachers/edit/*").hasAnyAuthority("ALL_PRIVILEGE", "EDIT_TEACHER")
                // Delete Privileges
                .mvcMatchers("/users/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_USER")
                .mvcMatchers("/years/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_YEAR")
                .mvcMatchers("/classes/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_CLASS")
                .mvcMatchers("/courses/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_COURSE")
                .mvcMatchers("/levels/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_LEVEL")
                .mvcMatchers("/employees/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_EMPLOYEE")
                .mvcMatchers("/guardians/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_GUARDIAN")
                .mvcMatchers("/jobs/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_JOB")
                .mvcMatchers("/roles/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_ROLE")
                .mvcMatchers("/schools/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_SCHOOL")
                .mvcMatchers("/students/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_STUDENT")
                .mvcMatchers("/transcripts/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_TRANSCRIPT")
                .mvcMatchers("/absences/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_ROLLCALL")
                .mvcMatchers("/studentGuardians/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_STUDENT_GUARDIAN")
                .mvcMatchers("/studentLeaves/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_LEAVE")
                .mvcMatchers("/courseLevels/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_COURSE_LEVEL")
                .mvcMatchers("/teachers/delete/*").hasAnyAuthority("ALL_PRIVILEGE", "DELETE_TEACHER")
                .antMatchers("/i18n/**"
                        ,"/webfonts/**"
                        ,"/css/**"
                        , "/images/**"
                        , "/js/**"
                        , "/webjars/**").permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                    .loginPage("/login").permitAll()
            .and()
                .exceptionHandling()
                    .accessDeniedPage("/accessDenied")
                    .accessDeniedHandler(accessDeniedHandler())
            .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout");
        // @formatter:on
    }
}
