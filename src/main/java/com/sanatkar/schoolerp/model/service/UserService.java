package com.sanatkar.schoolerp.model.service;

import com.sanatkar.schoolerp.controller.dto.UserRegistrationDto;
import com.sanatkar.schoolerp.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Create by ashkan on 2019/06/15
 */
public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User save(UserRegistrationDto registration);
}
