package com.sanatkar.schoolerp.model.entity;

import com.sanatkar.schoolerp.model.entity.enumeration.MarkType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by ashkan on 2020/01/05
 */
@Data
@NoArgsConstructor
@Entity
public class CourseLevel implements Serializable {

    private static final long serialVersionUID = 4449305024491911560L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MarkType markType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    private EducationLevel educationLevel;
}
