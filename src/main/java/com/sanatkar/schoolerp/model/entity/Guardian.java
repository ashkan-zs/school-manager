package com.sanatkar.schoolerp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class Guardian implements Serializable {

    private static final long serialVersionUID = 7354200120142321400L;

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

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "guardian")
    private List<StudentsGuardian> studentsGuardians = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_login_id")
    private User user;
}
