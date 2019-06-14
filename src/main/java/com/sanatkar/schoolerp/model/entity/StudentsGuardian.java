package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by ashkan on 2019/06/14
 */

@Data
@NoArgsConstructor
@Entity
public
class StudentsGuardian implements Serializable {

    private static final long serialVersionUID = -3400133425607662628L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "relation")
    private String relation;

    @ManyToOne
    @JsonIgnoreProperties("studentsGuardians")
    private Guardian guardian;

    @ManyToOne
    @JsonIgnoreProperties("studentsGuardians")
    private Student student;

}
