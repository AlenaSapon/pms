package com.pms.patientmanagementsystem.dto;

import java.util.List;

public record PatientDto(
    String id,
    String firstName,
    String lastName,
    List<PatientPatientCaseDto> patientCases,
    List<PatientDocumentDto> documents
) {
}
