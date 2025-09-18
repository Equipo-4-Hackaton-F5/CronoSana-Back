package com.equipo4hackathonf5.CronoSana.medicine;

import java.util.List;

import com.equipo4hackathonf5.CronoSana.implementations.IService;
import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineRequestDTO;
import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineResponseDTO;

public interface InterfaceMedicineService extends IService<MedicineResponseDTO, MedicineRequestDTO>{
    List<MedicineResponseDTO> getEntities();
    MedicineResponseDTO createEntity(MedicineRequestDTO medicineRequestDTO);
    MedicineResponseDTO getById(Long id);
    MedicineResponseDTO updateEntity(Long id, MedicineRequestDTO patientRequestDTO);
    void deleteEntity(Long id);
}
