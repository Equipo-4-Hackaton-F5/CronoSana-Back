package com.equipo4hackathonf5.CronoSana.dose_log;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equipo4hackathonf5.CronoSana.schedule.MedicineStatus;

public interface DoseLogRepository extends JpaRepository<DoseLogEntity, Long> {
    List<DoseLogEntity> findByScheduleId(Long scheduleId);

    List<DoseLogEntity> findByStatus(MedicineStatus status);
}
