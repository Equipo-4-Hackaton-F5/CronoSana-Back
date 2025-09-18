package com.equipo4hackathonf5.CronoSana.medicine;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipo4hackathonf5.CronoSana.implementations.IService;
import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineRequestDTO;
import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineResponseDTO;


@RestController
@RequestMapping(path = "${api-endpoint}medicines")
public class MedicineController {

    private final IService<MedicineResponseDTO, MedicineRequestDTO> service;

    public MedicineController(IService<MedicineResponseDTO, MedicineRequestDTO> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MedicineResponseDTO>> getAllMedicines() {
        return ResponseEntity.ok(service.getEntities());
    }

    @PostMapping
    public ResponseEntity<MedicineResponseDTO> createMedicine(@RequestBody MedicineRequestDTO dto) {
        MedicineResponseDTO createdMedicine = service.createEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponseDTO> getMedicineById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineResponseDTO> updateMedicine(
            @PathVariable Long id,
            @RequestBody MedicineRequestDTO dto) {
        return ResponseEntity.ok(service.updateEntity(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
