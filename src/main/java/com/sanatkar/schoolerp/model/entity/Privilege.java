package com.sanatkar.schoolerp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Create by ashkan on 2019/12/18
 */
@Data
@NoArgsConstructor
@Entity
public class Privilege implements Serializable {

    private static final long serialVersionUID = 4222243258741914334L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "privilege")
    private List<RolePrivilege> rolePrivileges;
}
