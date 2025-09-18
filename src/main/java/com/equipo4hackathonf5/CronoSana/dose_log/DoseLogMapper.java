package com.equipo4hackathonf5.CronoSana.dose_log;

import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogRequestDTO;
import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogResponseDTO;
import com.equipo4hackathonf5.CronoSana.schedule.MedicineStatus;
import com.equipo4hackathonf5.CronoSana.schedule.ScheduleEntity;

public class DoseLogMapper {
    public static DoseLogEntity toEntity(DoseLogRequestDTO dtoRequest, ScheduleEntity schedule) {
        DoseLogEntity doseLog = new DoseLogEntity();
        doseLog.setScheduleId(schedule);
        doseLog.setScheduledHour(dtoRequest.scheduledHour());
        doseLog.setTakenAt(dtoRequest.takenAt());
        doseLog.setStatus(dtoRequest.status() != null ? dtoRequest.status() : MedicineStatus.PENDING);

        return doseLog;
    }

    public static DoseLogResponseDTO toDTO(DoseLogEntity entity) {
        DoseLogResponseDTO dtoResponse = new DoseLogResponseDTO(
                entity.getId(),
                entity.getScheduleId().getId(),
                entity.getScheduledHour(),
                entity.getTakenAt(),
                entity.getStatus());

        return dtoResponse;
    }
}
