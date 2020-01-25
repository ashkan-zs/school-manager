package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
public class Employee implements Serializable {

    private static final long serialVersionUID = -260351853930545535L;

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

    @Column(name = "personal_id")
    private String personalId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "hired_date")
    private LocalDate hiredDate;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "teacher")
    private List<ClassTeacher> classTeachers = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("employees")
    private JobTitle title; //TODO: change this to role?!

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("employees")
    private School school;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_login_id")
    private User user;
}
