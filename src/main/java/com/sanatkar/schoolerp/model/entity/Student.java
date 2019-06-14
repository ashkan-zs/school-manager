package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sanatkar.schoolerp.model.entity.enumeration.Gender;
import com.sanatkar.schoolerp.model.entity.enumeration.Religion;
import com.sanatkar.schoolerp.model.entity.enumeration.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.sanatkar.schoolerp.model.entity.enumeration.BloodGroup;

/**
 * Create by ashkan on 2019/06/14
 */

@Data
@NoArgsConstructor
@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 2763273866363620825L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_code")
    private String nationalCode;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "student_no")
    private String studentNo;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "fathers_name")
    private String fathersName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroup bloodGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "religion")
    private Religion religion;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "mother_tongue")
    private String motherTongue;

    @Column(name = "nationality")
    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "last_state")
    private State lastState;

    @Column(name = "last_gpa")
    private String lastGPA;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "student")
    private Set<StudentAttendance> studentAttendances = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<StudentsGuardian> studentsGuardians = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Transcript> transcripts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("students")
    private ClassRoom classRoom;

    @ManyToOne
    @JsonIgnoreProperties("students")
    private EducationLevel level;

    @ManyToOne
    @JsonIgnoreProperties("students")
    private School school;
}
