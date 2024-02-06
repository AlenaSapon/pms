package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.StatusStatusHistoryDto;
import com.pms.patientmanagementsystem.model.StatusHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusStatusHistoryDtoConverter {
    public StatusStatusHistoryDto convert(StatusHistory from) {
        return new StatusStatusHistoryDto(
                from.getId(),
                from.getTime(),
                from.getDescription(),
                from.getAppointment() != null ? from.getAppointment().getId() : null
        );
    }

    public List<StatusStatusHistoryDto> convert(List<StatusHistory> from) {
        return from.stream().map(this::convert).toList();
    }
}
