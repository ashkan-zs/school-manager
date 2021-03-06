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
public class School implements Serializable {

    private static final long serialVersionUID = -1125808672656462395L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "area")
    private Integer area;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Column(name = "email")
    private String email;

    @Column(name = "is_government_school")
    private Boolean isGovernmentSchool;

    @OneToMany(mappedBy = "school")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    private List<ClassRoom> classRooms = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    private List<AcademicYear> academicYears = new ArrayList<>();
}
