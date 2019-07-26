package com.usman.sample.scheduler.demo.repository.temp;

import com.usman.sample.scheduler.demo.entity.temp.TempEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempEnrollmentRepository extends JpaRepository<TempEnrollment, Long> {
    TempEnrollment findByEnrollmentId(Long enrollmentId);
}
