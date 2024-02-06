package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.PatientPatientCaseDto;
import com.pms.patientmanagementsystem.model.PatientCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientPatientCaseDtoConverter {
    public PatientPatientCaseDto convert(PatientCase from) {
        return new PatientPatientCaseDto(
                from.getId(),
                from.getStartTime(),
                from.getEndTime(),
                from.getInProgress(),
                from.getTotalCost(),
                from.getAmountPaid()
        );
    }

    public List<PatientPatientCaseDto> convert(List<PatientCase> from) {
        return from.stream().map(this::convert).toList();
    }
}
