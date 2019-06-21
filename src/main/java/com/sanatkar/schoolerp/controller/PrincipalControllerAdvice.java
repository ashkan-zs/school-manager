package com.sanatkar.schoolerp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

/**
 * Create by ashkan on 2019/06/16
 */
@ControllerAdvice
public class PrincipalControllerAdvice {

    @ModelAttribute("currentUser")
    public Principal principal(Principal principal) {
        return principal;
    }
}
