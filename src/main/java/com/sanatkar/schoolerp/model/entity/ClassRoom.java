package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by ashkan on 2019/06/14
 */
@Data
@NoArgsConstructor
@Entity
public class ClassRoom implements Serializable {

    private static final long serialVersionUID = 6216762588609093736L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "classRoom")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "classRoom")
    private List<ClassTeacher> teachers = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("classRooms")
    private School school;
}
