package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sanatkar.schoolerp.model.entity.enumeration.MarkType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by ashkan on 2019/06/14
 */

@Data
@NoArgsConstructor
@Entity
public class EducationLevel implements Serializable {

    private static final long serialVersionUID = 5754227110636862887L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "base")
    private String base;

    @Enumerated(EnumType.STRING)
    @Column(name = "mark_type")
    private MarkType markType;

    @OneToMany(mappedBy = "level")
    private List<Student> students = new ArrayList<>();

    @ManyToMany(mappedBy = "levels")
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();
}
