package com.equipo4hackathonf5.CronoSana.dose_log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogRequestDTO;
import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogResponseDTO;
import com.equipo4hackathonf5.CronoSana.schedule.MedicineStatus;
import com.equipo4hackathonf5.CronoSana.schedule.ScheduleEntity;
import com.equipo4hackathonf5.CronoSana.schedule.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoseLogServiceImpl implements InterfaceDoseLogService {

    private final DoseLogRepository repository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<DoseLogResponseDTO> getEntities() {
        List<DoseLogResponseDTO> doseLogs = new ArrayList<>();

        repository.findAll().forEach(c -> {
            DoseLogResponseDTO dto = DoseLogMapper.toDTO(c);
            doseLogs.add(dto);
        });

        return doseLogs;
    }

    @Override
    public DoseLogResponseDTO createEntity(DoseLogRequestDTO dtoRequest) {
        ScheduleEntity schedule = scheduleRepository.findById(dtoRequest.scheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        DoseLogEntity doseLog = DoseLogMapper.toEntity(dtoRequest, schedule);
        DoseLogEntity saved = repository.save(doseLog);

        return DoseLogMapper.toDTO(saved);
    }

    @Override
    public DoseLogResponseDTO getById(Long id) {
        DoseLogEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoseLog no encontrada. Id " + id + " no existe."));
        return DoseLogMapper.toDTO(entity);
    }

    @Override
    public DoseLogResponseDTO updateEntity(Long id, DoseLogRequestDTO dtoRequest) {
        DoseLogEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoseLog no encontrada con id: " + id));

        if (dtoRequest.scheduledHour() != null)
            entity.setScheduledHour(dtoRequest.scheduledHour());
        if (dtoRequest.takenAt() != null)
            entity.setTakenAt(dtoRequest.takenAt());
        if (dtoRequest.status() != null)
            entity.setStatus(dtoRequest.status());

        DoseLogEntity updated = repository.save(entity);
        return DoseLogMapper.toDTO(updated);
    }

    @Override
    public void deleteEntity(Long id) {
        DoseLogEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoseLog no encontrada con id: " + id));
        repository.delete(entity);
    }

    public DoseLogResponseDTO markAsTaken(Long id) {
        DoseLogEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoseLog no encontrada"));

        entity.setStatus(MedicineStatus.TAKEN);
        entity.setTakenAt(LocalDateTime.now());

        DoseLogEntity updated = repository.save(entity);
        return DoseLogMapper.toDTO(updated);
    }

    public List<DoseLogResponseDTO> getBySchedule(Long scheduleId) {
        List<DoseLogResponseDTO> doseLogs = new ArrayList<>();

        repository.findByScheduleId(scheduleId).forEach(entity -> {
            doseLogs.add(DoseLogMapper.toDTO(entity));
        });

        return doseLogs;
    }

    public List<DoseLogResponseDTO> getPendingDoseLogs() {
        List<DoseLogResponseDTO> doseLogs = new ArrayList<>();

        repository.findByStatus(MedicineStatus.PENDING).forEach(entity -> {
            doseLogs.add(DoseLogMapper.toDTO(entity));
        });

        return doseLogs;
    }
}
