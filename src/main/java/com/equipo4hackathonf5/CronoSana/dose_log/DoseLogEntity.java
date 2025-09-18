package com.equipo4hackathonf5.CronoSana.dose_log;

import com.equipo4hackathonf5.CronoSana.schedule.MedicineStatus;
import com.equipo4hackathonf5.CronoSana.schedule.ScheduleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "dose_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoseLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;

    @Column(name = "scheduled_hour", nullable = false)
    private LocalDateTime scheduledHour;

    @Column(name = "taken_at", nullable = false)
    private LocalDateTime takenAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private MedicineStatus status = MedicineStatus.PENDING;
}
