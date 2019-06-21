package com.sanatkar.schoolerp.model.entity;

import com.sanatkar.schoolerp.model.entity.enumeration.AuthorityType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Create by ashkan on 2019/06/15
 */

@Data
@NoArgsConstructor
@Entity
public class Authority implements Serializable {

    private static final long serialVersionUID = 6260063799131599967L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    public Authority(@NotNull AuthorityType name) {
        this.name = name;
    }
}
