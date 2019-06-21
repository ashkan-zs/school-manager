package com.sanatkar.schoolerp.controller.dto;

import com.sanatkar.schoolerp.constraint.FieldMatch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Create by ashkan on 2019/06/15
 */
@Getter
@Setter
@NoArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match.")
public class UserRegistrationDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @AssertTrue
    private boolean terms;

    @NotNull
    private boolean active;
}
