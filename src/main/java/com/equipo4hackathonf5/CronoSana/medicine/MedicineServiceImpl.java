package com.equipo4hackathonf5.CronoSana.medicine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineRequestDTO;
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

    @Override
    public MedicineResponseDTO createEntity(MedicineRequestDTO medicineRequestDTO) {
        MedicineEntity medicine = MedicineMapper.toEntity(medicineRequestDTO);
        MedicineEntity medicineStored = repository.save(medicine);
        return MedicineMapper.toDTO(medicineStored) ;
    }

    @Override
    public MedicineResponseDTO getById(Long id) {
        MedicineEntity medicine = repository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento no encontrado. Id " + id + " no existe."));
        return MedicineMapper.toDTO(medicine);
    }

    @Override
    public MedicineResponseDTO updateEntity(Long id, MedicineRequestDTO medicineRequestDTO) {
        MedicineEntity medicine = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con id: " + id));

        medicine.setName(medicineRequestDTO.name());
        medicine.setDose(medicineRequestDTO.dose());

        MedicineEntity updatedEntity = repository.save(medicine);
        return MedicineMapper.toDTO(updatedEntity);

    }

    @Override
    public void deleteEntity(Long id) {
        MedicineEntity entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con id: " + id));
        repository.delete(entity);
    }



    
    
}
