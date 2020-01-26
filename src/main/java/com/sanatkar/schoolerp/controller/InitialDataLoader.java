package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.Employee;
import com.sanatkar.schoolerp.model.entity.Privilege;
import com.sanatkar.schoolerp.model.entity.Role;
import com.sanatkar.schoolerp.model.entity.User;
import com.sanatkar.schoolerp.model.repository.EmployeeDao;
import com.sanatkar.schoolerp.model.repository.PrivilegeDao;
import com.sanatkar.schoolerp.model.repository.RoleDao;
import com.sanatkar.schoolerp.model.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create by ashkan on 2019/12/18
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    private RoleDao roleDao;
    private PrivilegeDao privilegeDao;
    private EmployeeDao employeeDao;

    public InitialDataLoader(PasswordEncoder passwordEncoder, UserDao userDao, RoleDao roleDao, PrivilegeDao privilegeDao, EmployeeDao employeeDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.privilegeDao = privilegeDao;
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (alreadySetup) {
            return;
        }

        Privilege userPrivilege = createPrivilegeIfNotFound("SHOW_STUDENT");
        Privilege homePrivilege = createPrivilegeIfNotFound("HOME");
        Privilege allPrivilege = createPrivilegeIfNotFound("ALL_PRIVILEGE");

        List<Privilege> adminPrivilege = Arrays.asList(allPrivilege);

        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", adminPrivilege);
        Role roleUser = createRoleIfNotFound("ROLE_USER", Arrays.asList(homePrivilege));
        Role roleTeacher = createRoleIfNotFound("ROLE_TEACHER", Arrays.asList(userPrivilege, homePrivilege));

        createUserIfNotFound("admin", "admin", roleAdmin);
        createUserIfNotFound("user", "user", roleUser);
        User fateme = createUserIfNotFound("fateme", "fateme", roleTeacher);
        User ashkan = createUserIfNotFound("ashkan", "ashkan", roleTeacher);

//        createEmployeeIfNotFound(fateme, "fateme", "sohrabi");
//        createEmployeeIfNotFound(ashkan, "ashkan", "zarifian");

        alreadySetup = true;
    }

    @Transactional
    Employee createEmployeeIfNotFound(User user, String firstName, String lastName) {
        Employee employee = employeeDao.findByUser(user);

        if (employee == null) {
            employee = new Employee();
            employee.setUser(user);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employeeDao.save(employee);
        }

        return employee;
    }
    @Transactional
    User createUserIfNotFound(String username, String password, Role role) {
        User user = userDao.findByUsername(username);

        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setCreateDate(LocalDateTime.now());
            user.setActive(true);
            user.setRoles(Collections.singletonList(role));
            userDao.save(user);
        }
        return user;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeDao.findByName(name);

        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeDao.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, List<Privilege> privilegeList) {

        Role role = roleDao.findByName(name);

        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privilegeList);
            roleDao.save(role);
        }
        return role;
    }
}
