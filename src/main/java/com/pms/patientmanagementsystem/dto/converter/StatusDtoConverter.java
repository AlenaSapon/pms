package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.StatusDto;
import com.pms.patientmanagementsystem.model.Status;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusDtoConverter {
    private final StatusAppointmentDtoConverter appointmentDtoConverter;
    private final StatusStatusHistoryDtoConverter statusHistoryDtoConverter;

    public StatusDtoConverter(StatusAppointmentDtoConverter appointmentDtoConverter,
                              StatusStatusHistoryDtoConverter statusHistoryDtoConverter) {
        this.appointmentDtoConverter = appointmentDtoConverter;
        this.statusHistoryDtoConverter = statusHistoryDtoConverter;
    }

    public StatusDto convert(Status from) {
        return new StatusDto(
                from.getId(),
                from.getName(),
                from.getAppointments() != null ? appointmentDtoConverter.convert(from.getAppointments()) : null,
                from.getStatusHistories() != null ? statusHistoryDtoConverter.convert(from.getStatusHistories()) : null
        );
    }

    public List<StatusDto> convert(List<Status> from) {
        return from.stream().map(this::convert).toList();
    }
}
