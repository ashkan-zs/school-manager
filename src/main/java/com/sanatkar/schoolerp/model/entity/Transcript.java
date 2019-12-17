package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by ashkan on 2019/06/14
 */

@Data
@NoArgsConstructor
@Entity
public class Transcript implements Serializable {

    private static final long serialVersionUID = 4133582125446070408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade")
    private String grade;

    @Column(name = "jhi_advise")
    private String advise;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_type")
    private String type;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("transcripts")
    private Course course;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("transcripts")
    private AcademicYear year;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("transcripts")
    private Student student;

}
