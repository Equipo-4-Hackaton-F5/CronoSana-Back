package com.equipo4hackathonf5.CronoSana.dose_log;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoseLogServiceImpl implements InterfaceDoseLogService {

    private final DoseLogRepository repository;

    @Override
    public List<DoseLogResponseDTO> getDoseLogEntities() {
        List<DoseLogResponseDTO> doseLogs = new ArrayList<>();

        repository.findAll().forEach(entity -> {
            DoseLogResponseDTO dto = DoseLogMapper.toDTO(entity);
            doseLogs.add(dto);
        });

        return doseLogs;
    }

}
