package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "level")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "educationLevel")
    @JsonIgnore
    private List<CourseLevel> courseLevels;
}
