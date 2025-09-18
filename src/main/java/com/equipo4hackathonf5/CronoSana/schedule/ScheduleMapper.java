package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleRequestDTO;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {
    private final ScheduleMapperHelper scheduleMapperHelper;

    public ScheduleEntity toEntity(ScheduleRequestDTO dtoRequest) {
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setFirstTake(dtoRequest.firstTake());
        schedule.setTimesPerDay(dtoRequest.timesPerDay());
        schedule.setMedicineId(scheduleMapperHelper.medicineIdToEntity(dtoRequest.medicineId()));

        return schedule;
    }

    public ScheduleResponseDTO toDTO(ScheduleEntity entity) {
        ScheduleResponseDTO dtoResponse = new ScheduleResponseDTO(entity.getId(), entity.getFirstTake(), entity.getTimesPerDay(), entity.getMedicineId().getId());

        return dtoResponse;
    }

}
