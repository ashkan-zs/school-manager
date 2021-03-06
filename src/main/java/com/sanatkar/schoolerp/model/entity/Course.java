package com.sanatkar.schoolerp.model.entity;

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
public class Course implements Serializable {

    private static final long serialVersionUID = 546283249858534727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_type")
    private String courseType;

    @OneToMany(mappedBy = "course")
    private List<CourseLevel> courseLevels = new ArrayList<>();
}
