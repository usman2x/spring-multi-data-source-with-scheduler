package com.usman.sample.scheduler.demo.demo;


import com.usman.sample.scheduler.demo.dto.EnrollmentDto;
import com.usman.sample.scheduler.demo.service.DestinationEnrollmentService;
import com.usman.sample.scheduler.demo.service.TempEnrollmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CronRunner {

    private final DestinationEnrollmentService destinationEnrollmentService;
    private final TempEnrollmentService tempEnrollmentService;

    public CronRunner(DestinationEnrollmentService destinationEnrollmentService, TempEnrollmentService tempEnrollmentService) {
        this.destinationEnrollmentService = destinationEnrollmentService;
        this.tempEnrollmentService = tempEnrollmentService;
    }


    @Scheduled(fixedDelay = 60000L)
    public void syncDb() {
        log.info("Db Synchronization job starts.");
        List<EnrollmentDto> enrollmentDtoList = tempEnrollmentService.getEnrollmentDtoList();
        enrollmentDtoList.forEach(enrollmentDto -> {
            destinationEnrollmentService.addOrUpdateEnrollment(enrollmentDto);
            tempEnrollmentService.deleteEnrollmentById(enrollmentDto.getId());
        });
        log.info("Db Synchronization job ends.");
    }
}
