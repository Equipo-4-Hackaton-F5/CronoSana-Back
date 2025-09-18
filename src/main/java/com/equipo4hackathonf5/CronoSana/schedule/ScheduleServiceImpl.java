package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleRequestDTO;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements InterfaceScheduleService {
    private final ScheduleRepository repository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleResponseDTO> getEntities() {
        List<ScheduleResponseDTO> schedules = new ArrayList<>();

        repository.findAll().forEach(c -> {
            ScheduleResponseDTO schedule = scheduleMapper.toDTO(c);
            schedules.add(schedule);
        });

        return schedules;
    }

    @Override
    public ScheduleResponseDTO createEntity(ScheduleRequestDTO scheduleRequestDTO) {
        ScheduleEntity schedule = scheduleMapper.toEntity(scheduleRequestDTO);
        ScheduleEntity scheduleStored = repository.save(schedule);
        return scheduleMapper.toDTO(scheduleStored);
    }

    @Override
    public ScheduleResponseDTO getById(Long id) {
        ScheduleEntity schedule = repository.findById(id).orElseThrow(() -> new RuntimeException("Programación no encontrada. Id " + id + " no existe."));
        return scheduleMapper.toDTO(schedule);
    }

    @Override
    public ScheduleResponseDTO updateEntity(Long id, ScheduleRequestDTO ScheduleRequestDTO) {
        ScheduleEntity schedule = repository.findById(id).orElseThrow(() -> new RuntimeException("Programación no encontrada con id: " + id));

        schedule.setFirstTake(ScheduleRequestDTO.firstTake());
        schedule.setTimesPerDay(ScheduleRequestDTO.timesPerDay());

        ScheduleEntity updatedEntity = repository.save(schedule);
        return scheduleMapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteEntity(Long id) {
        ScheduleEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Programación no encontrada con id: " + id));
        repository.delete(entity);
    }
}
