package com.usman.sample.scheduler.demo.dto;

import lombok.Data;

@Data
public class EnrollmentDto {
    private Long id;

    private Long enrollmentId;

    private String mobileNumber;

    private String email;

    private String address;

    private String action;
}
