package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Create by ashkan on 2019/06/14
 */

@Data
@NoArgsConstructor
@Entity
public class StudentAttendance implements Serializable {

    private static final long serialVersionUID = 1274621371926723489L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_acceptable")
    private Boolean isAcceptable;

    @Column(name = "reason")
    private String reason;

    @Column(name = "absence_date")
    private LocalDate absenceDate;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("studentAttendances")
    private AcademicYear year;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("studentAttendances")
    private Student student;

}
