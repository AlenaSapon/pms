package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.StatusHistoryDto;
import com.pms.patientmanagementsystem.model.StatusHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusHistoryDtoConverter {
    public StatusHistoryDto convert(StatusHistory from) {
        return new StatusHistoryDto(
                from.getId(),
                from.getTime(),
                from.getDescription(),
                from.getStatus() != null ? from.getStatus().getId() : null,
                from.getAppointment() != null ? from.getAppointment().getId() : null
        );
    }

    public List<StatusHistoryDto> convert(List<StatusHistory> from) {
        return from.stream().map(this::convert).toList();
    }
}
