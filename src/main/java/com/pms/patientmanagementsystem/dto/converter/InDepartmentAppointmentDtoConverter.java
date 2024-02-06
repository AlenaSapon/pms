package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.InDepartmentAppointmentDto;
import com.pms.patientmanagementsystem.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InDepartmentAppointmentDtoConverter {
    public InDepartmentAppointmentDto convert(Appointment from) {
        return new InDepartmentAppointmentDto(
                from.getId(),
                from.getStartTime(),
                from.getEndTime(),
                from.getStatus() != null ? from.getStatus().getId() : null,
                from.getPatientCase() != null ? from.getPatientCase().getId() : null
        );
    }

    public List<InDepartmentAppointmentDto> convert(List<Appointment> from) {
        return from.stream().map(this::convert).toList();
    }
}
