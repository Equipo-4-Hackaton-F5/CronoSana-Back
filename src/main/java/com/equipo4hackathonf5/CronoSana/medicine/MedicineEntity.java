package com.equipo4hackathonf5.CronoSana.medicine;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "medicine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "dose", nullable = false)
    private String dose;

    @Column(name = "scheduled_hour", nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalDateTime scheduledHour;

    @Column(name = "taken_hour")
    private LocalDateTime takenHour;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private MedicineStatus status = MedicineStatus.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
