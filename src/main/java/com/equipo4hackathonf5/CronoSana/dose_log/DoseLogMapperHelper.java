package com.equipo4hackathonf5.CronoSana.dose_log;

import com.equipo4hackathonf5.CronoSana.schedule.ScheduleEntity;
import com.equipo4hackathonf5.CronoSana.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoseLogMapperHelper {
    private final ScheduleRepository scheduleRepository;

    public ScheduleEntity scheduleIdToEntity (Long id) {
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Programación no encontrada. Id " + id + " no existe."));
        return schedule;
    }
}
