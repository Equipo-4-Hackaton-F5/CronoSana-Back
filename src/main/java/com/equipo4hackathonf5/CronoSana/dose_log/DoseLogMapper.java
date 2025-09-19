package com.equipo4hackathonf5.CronoSana.dose_log;

import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogRequestDTO;
import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogResponseDTO;
import com.equipo4hackathonf5.CronoSana.schedule.MedicineStatus;
import com.equipo4hackathonf5.CronoSana.schedule.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoseLogMapper {
    private final DoseLogMapperHelper doseLogMapperHelper;

    public DoseLogEntity toEntity(DoseLogRequestDTO dtoRequest, ScheduleEntity schedule) {
        DoseLogEntity doseLog = new DoseLogEntity();
        doseLog.setSchedule(doseLogMapperHelper.scheduleIdToEntity(dtoRequest.scheduleId()));
        doseLog.setScheduledHour(dtoRequest.scheduledHour());
        doseLog.setTakenAt(dtoRequest.takenAt());
        doseLog.setStatus(dtoRequest.status() != null ? dtoRequest.status() : MedicineStatus.PENDING);

        return doseLog;
    }

    public DoseLogResponseDTO toDTO(DoseLogEntity entity) {
        DoseLogResponseDTO dtoResponse = new DoseLogResponseDTO(
                entity.getId(),
                entity.getSchedule().getId(),
                entity.getScheduledHour(),
                entity.getTakenAt(),
                entity.getStatus());

        return dtoResponse;
    }
}
