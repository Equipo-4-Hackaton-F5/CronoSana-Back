package com.equipo4hackathonf5.CronoSana.medicine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements InterfaceMedicineService{

    private final MedicineRepository repository;

    @Override
    public List<MedicineResponseDTO> getEntities() {
        List<MedicineResponseDTO> medicines = new ArrayList<>();

        repository.findAll().forEach(c -> {
            MedicineResponseDTO medicine = MedicineMapper.toDTO(c);
            medicines.add(medicine);
        });

        return medicines;
    }



    
    
}
