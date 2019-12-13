package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sanatkar.schoolerp.model.entity.enumeration.BloodGroup;
import com.sanatkar.schoolerp.model.entity.enumeration.Gender;
import com.sanatkar.schoolerp.model.entity.enumeration.Religion;
import com.sanatkar.schoolerp.model.entity.enumeration.State;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 10,max = 10)
    @Column(name = "national_code")
    private String nationalCode;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "student_no")
    private String studentNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    private List<StudentAttendance> studentAttendances = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<StudentsGuardian> studentsGuardians = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Transcript> transcripts = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("students")
    private ClassRoom classRoom;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("students")
    private EducationLevel level;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("students")
    private School school;
}
