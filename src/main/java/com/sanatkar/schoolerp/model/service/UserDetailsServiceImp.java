package com.sanatkar.schoolerp.model.service;

import com.sanatkar.schoolerp.model.entity.User;
import com.sanatkar.schoolerp.model.entity.UserDetailsImp;
import com.sanatkar.schoolerp.model.repository.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Create by ashkan on 2019/06/15
 */

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private UserDao userDao;

    public UserDetailsServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userDao.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }

        return new UserDetailsImp(user);
    }
}
