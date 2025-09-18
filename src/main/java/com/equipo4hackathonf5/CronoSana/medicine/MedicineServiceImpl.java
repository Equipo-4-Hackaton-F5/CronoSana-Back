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
    public List<MedicineResponseDTO> getMedicineEntities() {
        List<MedicineResponseDTO> medicines = new ArrayList<>();

        repository.findAll().forEach(c -> {
            MedicineResponseDTO medicine = MedicineMapper.toDTO(c);
            medicines.add(medicine);
        });

        return medicines;
    }

    /* @Override
    public MedicineResponseDTO createEntity(MedicineRequestDTO patientRequestDTO) {
        MedicineEntity patient = MediciMapper.toEntity(patientRequestDTO);
        PatientEntity patientStored = repository.save(patient);
        return PatientMapper.toDTO(patientStored) ;
    }

    @Override
    public PatientResponseDTO getById(Long id) {
        PatientEntity patient = repository.findById(id).orElseThrow(() -> new PatientNotFoundExceptions("Paciente no encontrado. Id " + id + " no existe."));
        return PatientMapper.toDTO(patient);
    } */



    
    
}
