package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.PatientCaseDocumentDto;
import com.pms.patientmanagementsystem.model.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientCaseDocumentDtoConverter {
    public PatientCaseDocumentDto convert(Document from) {
        return new PatientCaseDocumentDto(
                from.getId(),
                from.getDocumentInternalId(),
                from.getName(),
                from.getUrl(),
                from.getDetail(),
                from.getDocumentType() != null ? from.getDocumentType().getId() : null,
                from.getAppointment() != null ? from.getAppointment().getId() : null,
                from.getPatient() != null ? from.getPatient().getId() : null,
                from.getInDepartment() != null ? from.getInDepartment().getId() : null
        );
    }

    public List<PatientCaseDocumentDto> convert(List<Document> from) {
        return from.stream().map(this::convert).toList();
    }
}
