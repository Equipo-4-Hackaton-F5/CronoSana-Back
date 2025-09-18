package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.implementations.IService;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleRequestDTO;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleResponseDTO;

public interface InterfaceScheduleService extends IService<ScheduleResponseDTO, ScheduleRequestDTO> {
    ScheduleResponseDTO createEntity(ScheduleRequestDTO scheduleRequestDTO);
}
