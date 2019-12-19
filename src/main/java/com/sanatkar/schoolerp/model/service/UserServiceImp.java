package com.sanatkar.schoolerp.model.service;

import com.sanatkar.schoolerp.controller.dto.UserRegistrationDto;
import com.sanatkar.schoolerp.model.entity.User;
import com.sanatkar.schoolerp.model.entity.UserDetailsImp;
import com.sanatkar.schoolerp.model.repository.RoleDao;
import com.sanatkar.schoolerp.model.repository.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * Create by ashkan on 2019/06/15
 */

@Service("userDetailService")
@Transactional
public class UserServiceImp implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImp(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User save(UserRegistrationDto registration) {

        User user = new User();
        user.setUsername(registration.getUsername());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setActive(registration.isActive());
        user.setCreateDate(LocalDateTime.now());
        user.setRoles(registration.getRole());

        return userDao.save(user);
    }
}
