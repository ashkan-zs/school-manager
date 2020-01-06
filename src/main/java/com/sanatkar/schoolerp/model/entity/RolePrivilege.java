package com.sanatkar.schoolerp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Create by ashkan on 2020/01/04
 */
@Data
@NoArgsConstructor
@Entity
public class RolePrivilege implements Serializable {

    private static final long serialVersionUID = 2491279288243280996L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Privilege privilege;
}
