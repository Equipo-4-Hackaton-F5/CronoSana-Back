package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleRequestDTO;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    public static ScheduleEntity toEntity(ScheduleRequestDTO dtoRequest) {
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setFirstTake(dtoRequest.firstTake());
        schedule.setTimesPerDay(dtoRequest.timesPerDay());

        //TODO make a helper function to map medicine
        //schedule.setMedicineId(dtoRequest.medicineId());

        return schedule;
    }

    public static ScheduleResponseDTO toDTO(ScheduleEntity entity) {
        ScheduleResponseDTO dtoResponse = new ScheduleResponseDTO(
                entity.getId(),
                entity.getFirstTake(),
                entity.getTimesPerDay(),
                entity.getMedicineId().getId()
        );

        return dtoResponse;
    }

}
