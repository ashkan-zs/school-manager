package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Create by ashkan on 2019/06/14
 */

@Data
@NoArgsConstructor
@Entity
public class AcademicYear implements Serializable {

    private static final long serialVersionUID = 6356181494797313754L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "from_year")
    private LocalDate fromYear;

    @Column(name = "to_year")
    private LocalDate toYear;

    @ManyToOne
    @JsonIgnoreProperties("academicYears")
    private School school;
}
