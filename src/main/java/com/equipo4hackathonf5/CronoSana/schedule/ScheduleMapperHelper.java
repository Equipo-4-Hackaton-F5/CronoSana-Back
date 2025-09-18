package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.medicine.MedicineEntity;
import com.equipo4hackathonf5.CronoSana.medicine.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleMapperHelper {
    private final MedicineRepository medicineRepository;

    public MedicineEntity medicineIdToEntity (Long id) {
        MedicineEntity medicine = medicineRepository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento no encontrado. Id " + id + " no existe."));
        return medicine;
    }
}
