package com.usman.sample.scheduler.demo.service;

import com.usman.sample.scheduler.demo.dto.EnrollmentDto;
import com.usman.sample.scheduler.demo.entity.temp.TempEnrollment;
import com.usman.sample.scheduler.demo.repository.temp.TempEnrollmentRepository;
import com.usman.sample.scheduler.demo.utility.MapperUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TempEnrollmentService {
    private final MapperUtility mapperUtility;
    private final TempEnrollmentRepository tempEnrollmentRepository;

    public TempEnrollmentService(MapperUtility mapperUtility, TempEnrollmentRepository tempEnrollmentRepository) {
        this.mapperUtility = mapperUtility;
        this.tempEnrollmentRepository = tempEnrollmentRepository;
    }

    public List<EnrollmentDto> getEnrollmentDtoList() {
        List<TempEnrollment> tempEnrollmentList = tempEnrollmentRepository.findAll();
        return tempEnrollmentList.stream().map(mapperUtility::convertToDto).collect(Collectors.toList());
    }

    public List<TempEnrollment> getEnrollmentList() {
        return tempEnrollmentRepository.findAll();
    }

    public void deleteEnrollmentById(Long enrollmentId) {
        tempEnrollmentRepository.delete(getEnrollment(enrollmentId));
    }

    private TempEnrollment getEnrollment(Long enrollment) {
        return tempEnrollmentRepository.getOne(enrollment);
    }

    private TempEnrollment getEnrollmentByEnrollmentId(Long enrollmentId) {
        return tempEnrollmentRepository.findByEnrollmentId(enrollmentId);
    }
}
