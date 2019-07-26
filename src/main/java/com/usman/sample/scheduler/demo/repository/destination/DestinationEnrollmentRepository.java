package com.usman.sample.scheduler.demo.repository.destination;

import com.usman.sample.scheduler.demo.entity.destination.DestinationEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationEnrollmentRepository extends JpaRepository<DestinationEnrollment, Long> {
    DestinationEnrollment findByEnrollmentId(Long enrollmentId);
}
