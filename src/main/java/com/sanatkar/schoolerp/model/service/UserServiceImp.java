package com.sanatkar.schoolerp.model.service;

import com.sanatkar.schoolerp.controller.dto.UserRegistrationDto;
import com.sanatkar.schoolerp.model.entity.User;
import com.sanatkar.schoolerp.model.entity.UserDetailsImp;
import com.sanatkar.schoolerp.model.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Create by ashkan on 2019/06/15
 */

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
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
        user.setAuthorities(registration.getAuthority());

        return userDao.save(user);
    }
}
