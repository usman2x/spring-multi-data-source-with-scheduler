package com.usman.sample.scheduler.demo.utility;


import com.usman.sample.scheduler.demo.dto.EnrollmentDto;
import com.usman.sample.scheduler.demo.entity.destination.DestinationEnrollment;
import com.usman.sample.scheduler.demo.entity.temp.TempEnrollment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MapperUtility {

    private ModelMapper modelMapper;

    @PostConstruct
    private void initModelMapper() {
        modelMapper = new ModelMapper();
    }

    public EnrollmentDto convertToDto(TempEnrollment tempEnrollment) {
        return modelMapper.map(tempEnrollment, EnrollmentDto.class);
    }

    public TempEnrollment convertToEntity(EnrollmentDto enrollmentDto) {
        return modelMapper.map(enrollmentDto, TempEnrollment.class);
    }

    public DestinationEnrollment convertToDestinationEntity(EnrollmentDto enrollmentDto) {
        return modelMapper.map(enrollmentDto, DestinationEnrollment.class);
    }
}
