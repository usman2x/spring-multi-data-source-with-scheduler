package com.usman.sample.scheduler.demo.service;

import com.usman.sample.scheduler.demo.dto.EnrollmentDto;
import com.usman.sample.scheduler.demo.entity.destination.DestinationEnrollment;
import com.usman.sample.scheduler.demo.repository.destination.DestinationEnrollmentRepository;
import com.usman.sample.scheduler.demo.utility.MapperUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DestinationEnrollmentService {
    private final DestinationEnrollmentRepository destinationEnrollmentRepository;
    private final MapperUtility mapperUtility;

    public DestinationEnrollmentService(DestinationEnrollmentRepository destinationEnrollmentRepository, MapperUtility mapperUtility) {
        this.destinationEnrollmentRepository = destinationEnrollmentRepository;
        this.mapperUtility = mapperUtility;
    }

    @Transactional("destinationTransactionManager")
    public void addOrUpdateEnrollment(EnrollmentDto enrollmentDto) {
        DestinationEnrollment oldTempEnrollment = destinationEnrollmentRepository.findByEnrollmentId(enrollmentDto.getEnrollmentId());

        if (oldTempEnrollment != null) {
            enrollmentDto.setId(oldTempEnrollment.getId());
        }

        destinationEnrollmentRepository.save(mapperUtility.convertToDestinationEntity(enrollmentDto));
    }
}
