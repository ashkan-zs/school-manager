package com.sanatkar.schoolerp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sanatkar.schoolerp.model.entity.enumeration.LeaveStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Create by ashkan on 2019/06/14
 */

@Data
@NoArgsConstructor
@Entity
public class StudentLeaveRequest implements Serializable {

    private static final long serialVersionUID = -7275821006073699376L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_date")
    private LocalDate requestDate;

    @Column(name = "leave_date")
    private LocalDate leaveDate;

    @Column(name = "reason")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LeaveStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("studentLeaveRequests")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("studentLeaveRequests")
    private Guardian guardian;

}
