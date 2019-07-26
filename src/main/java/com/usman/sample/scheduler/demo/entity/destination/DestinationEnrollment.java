package com.usman.sample.scheduler.demo.entity.destination;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "enrollments")
public class DestinationEnrollment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @Basic
    @Column(name = "mobile_number")
    private String mobileNumber;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "action")
    private String action;
}
