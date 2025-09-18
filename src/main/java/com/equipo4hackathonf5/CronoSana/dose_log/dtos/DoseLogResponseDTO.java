package com.equipo4hackathonf5.CronoSana.dose_log.dtos;

import java.time.LocalDateTime;

import com.equipo4hackathonf5.CronoSana.schedule.MedicineStatus;

public record DoseLogResponseDTO(Long id, Long scheduleId, LocalDateTime scheduledHour, LocalDateTime takenAt,
        MedicineStatus status) {

}
