package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.Privilege;
import com.sanatkar.schoolerp.model.entity.Role;
import com.sanatkar.schoolerp.model.entity.User;
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
import java.util.List;

/**
 * Create by ashkan on 2019/12/18
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PrivilegeDao privilegeDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (alreadySetup) {
            return;
        }

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivilege = Arrays.asList(readPrivilege, writePrivilege);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivilege);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleDao.findByName("ROLE_ADMIN");
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setCreateDate(LocalDateTime.now());
        user.setActive(true);
        user.setRoles(Arrays.asList(adminRole));
        userDao.save(user);

        Role userRole = roleDao.findByName("ROLE_USER");
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("user"));
        user1.setCreateDate(LocalDateTime.now());
        user1.setActive(true);
        user1.setRoles(Arrays.asList(userRole));
        userDao.save(user1);

        alreadySetup = false;
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
