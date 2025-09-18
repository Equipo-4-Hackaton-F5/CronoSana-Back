package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.implementations.IService;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleRequestDTO;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final IService<ScheduleResponseDTO, ScheduleRequestDTO> service;

    @GetMapping("")
    public ResponseEntity<List<ScheduleResponseDTO>> getAllSchedules() {
        return ResponseEntity.ok(service.getEntities());
    }

    @PostMapping("")
    public ResponseEntity<ScheduleResponseDTO> createSchedule(@RequestBody ScheduleRequestDTO dto) {
        ScheduleResponseDTO createSchedule = service.createEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDTO dto) {
        return ResponseEntity.ok(service.updateEntity(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
