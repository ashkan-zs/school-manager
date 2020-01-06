package com.sanatkar.schoolerp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Create by ashkan on 2019/06/15
 */

@Data
@NoArgsConstructor
@Entity
public class Role implements Serializable {

    private static final long serialVersionUID = 6260063799131599967L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
//    @Enumerated(EnumType.STRING)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "role")
    private List<RolePrivilege> rolePrivileges;

}
