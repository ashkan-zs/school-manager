package com.sanatkar.schoolerp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by ashkan on 2020/01/05
 */
@Data
@NoArgsConstructor
@Entity
public class UserRole implements Serializable {

    private static final long serialVersionUID = 3599403671328381128L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
