package com.equipo4hackathonf5.CronoSana.schedule.dtos;

import java.time.LocalDateTime;

public record ScheduleResponseDTO(
        Long id,
        LocalDateTime firstTake,
        Integer timesPerDay,
        Long medicineId
) {
}
