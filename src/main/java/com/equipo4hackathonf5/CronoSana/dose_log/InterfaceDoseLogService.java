package com.equipo4hackathonf5.CronoSana.dose_log;

import java.util.List;

import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogRequestDTO;
import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogResponseDTO;
import com.equipo4hackathonf5.CronoSana.implementations.IService;

public interface InterfaceDoseLogService extends IService<DoseLogResponseDTO, DoseLogRequestDTO> {

    DoseLogResponseDTO markAsTaken(Long id);

    List<DoseLogResponseDTO> getBySchedule(Long scheduleId);

    List<DoseLogResponseDTO> getPendingDoseLogs();
}
