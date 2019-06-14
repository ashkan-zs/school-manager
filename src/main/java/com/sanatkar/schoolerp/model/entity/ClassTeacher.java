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
public class ClassTeacher implements Serializable {

    private static final long serialVersionUID = -6771333111508385749L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "is_primary_teacher")
    private Boolean isPrimaryTeacher;

    @ManyToOne
    @JsonIgnoreProperties("teachers")
    private ClassRoom classRoom;

    @ManyToOne
    @JsonIgnoreProperties("classTeachers")
    private Employee teacher;
}
