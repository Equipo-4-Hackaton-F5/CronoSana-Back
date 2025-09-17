package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.medicine.MedicineEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_take", nullable = false)
    private LocalDateTime firstTake;

    @Column(name = "times_per_day")
    private Integer timesPerDay;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicineId;
}
