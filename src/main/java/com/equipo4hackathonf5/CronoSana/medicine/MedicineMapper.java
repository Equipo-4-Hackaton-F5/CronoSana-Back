package com.equipo4hackathonf5.CronoSana.medicine;

import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineRequestDTO;
import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineResponseDTO;

public class MedicineMapper {
    public static MedicineEntity toEntity(MedicineRequestDTO dtoRequest) {
        MedicineEntity medicine = new MedicineEntity();
        medicine.setName(dtoRequest.name());
        medicine.setDose(dtoRequest.dose());

        return medicine;
    }

    public static MedicineResponseDTO toDTO(MedicineEntity entity) {
        MedicineResponseDTO dtoResponse = new MedicineResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getDose()
        );

        return dtoResponse;
    }
}
