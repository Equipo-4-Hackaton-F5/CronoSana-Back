package com.equipo4hackathonf5.CronoSana.dose_log;

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

import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogRequestDTO;
import com.equipo4hackathonf5.CronoSana.dose_log.dtos.DoseLogResponseDTO;

@RestController
@RequestMapping("/api/dose-logs")
public class DoseLogController {
    private final InterfaceDoseLogService service;

    public DoseLogController(InterfaceDoseLogService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<DoseLogResponseDTO>> getAllDoseLogs() {
        return ResponseEntity.ok(service.getEntities());
    }

    @PostMapping("")
    public ResponseEntity<DoseLogResponseDTO> createDoseLog(@RequestBody DoseLogRequestDTO dto) {
        DoseLogResponseDTO created = service.createEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoseLogResponseDTO> getDoseLogById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoseLogResponseDTO> updateDoseLog(
            @PathVariable Long id,
            @RequestBody DoseLogRequestDTO dto) {
        return ResponseEntity.ok(service.updateEntity(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoseLog(@PathVariable Long id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
