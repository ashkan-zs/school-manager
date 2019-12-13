package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by ashkan on 2019/06/15
 */

@Data
@NoArgsConstructor
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -4862596219645755870L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @NotNull
    @Column(nullable = false)
    private String password;

    private LocalDateTime createDate;

    @NotNull
    @Column(nullable = false)
    private boolean active = false;

    @NotNull
    @Column(nullable = false)
    private boolean locked = false;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities = new ArrayList<>();

}
