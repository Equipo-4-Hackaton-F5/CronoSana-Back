package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.medicine.MedicineEntity;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleRequestDTO;
import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for ScheduleService")
class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository repository;

    @Mock
    private ScheduleMapper scheduleMapper;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    private ScheduleRequestDTO scheduleRequestDTO;
    private ScheduleResponseDTO scheduleResponseDTO;
    private ScheduleEntity scheduleEntity;
    private ScheduleEntity scheduleEntity2;
    private MedicineEntity medicineEntity;

    @BeforeEach
    void setUp() {
        medicineEntity = MedicineEntity.builder()
                .id(1L)
                .name("Paracetamol")
                .dose("500mg")
                .build();

        scheduleRequestDTO = new ScheduleRequestDTO(
                LocalDateTime.of(2024, 1, 15, 8, 0),
                3,
                1L
        );

        scheduleEntity = ScheduleEntity.builder()
                .id(1L)
                .firstTake(LocalDateTime.of(2024, 1, 15, 8, 0))
                .timesPerDay(3)
                .medicineId(medicineEntity)
                .build();

        scheduleEntity2 = ScheduleEntity.builder()
                .id(2L)
                .firstTake(LocalDateTime.of(2024, 1, 15, 9, 0))
                .timesPerDay(2)
                .medicineId(medicineEntity)
                .build();

        scheduleResponseDTO = new ScheduleResponseDTO(
                1L,
                LocalDateTime.of(2024, 1, 15, 8, 0),
                3,
                1L
        );
    }

    @Nested
    @DisplayName("Get all entities")
    class GetAllEntitiesTests {

        @Test
        @DisplayName("When schedules exist, it should return all schedules")
        void getEntities_whenSchedulesExist_shouldReturnAllSchedules() {
            List<ScheduleEntity> scheduleEntities = List.of(scheduleEntity, scheduleEntity2);
            ScheduleResponseDTO scheduleResponseDTO2 = new ScheduleResponseDTO(
                    2L,
                    LocalDateTime.of(2024, 1, 15, 9, 0),
                    2,
                    1L
            );

            when(repository.findAll()).thenReturn(scheduleEntities);
            when(scheduleMapper.toDTO(scheduleEntity)).thenReturn(scheduleResponseDTO);
            when(scheduleMapper.toDTO(scheduleEntity2)).thenReturn(scheduleResponseDTO2);

            List<ScheduleResponseDTO> result = scheduleService.getEntities();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(scheduleResponseDTO, result.get(0));
            assertEquals(scheduleResponseDTO2, result.get(1));

            verify(repository, times(1)).findAll();
            verify(scheduleMapper, times(2)).toDTO(any(ScheduleEntity.class));
        }

        @Test
        @DisplayName("When no schedules exist, it should return empty list")
        void getEntities_whenNoSchedulesExist_shouldReturnEmptyList() {
            when(repository.findAll()).thenReturn(List.of());

            List<ScheduleResponseDTO> result = scheduleService.getEntities();

            assertNotNull(result);
            assertTrue(result.isEmpty());

            verify(repository, times(1)).findAll();
            verifyNoInteractions(scheduleMapper);
        }
    }

    @Nested
    @DisplayName("Create entity")
    class CreateEntityTests {

        @Test
        @DisplayName("When request is valid, it should create schedule successfully")
        void createEntity_whenValidRequest_shouldCreateScheduleSuccessfully() {
            when(scheduleMapper.toEntity(scheduleRequestDTO)).thenReturn(scheduleEntity);
            when(repository.save(scheduleEntity)).thenReturn(scheduleEntity);
            when(scheduleMapper.toDTO(scheduleEntity)).thenReturn(scheduleResponseDTO);

            ScheduleResponseDTO result = scheduleService.createEntity(scheduleRequestDTO);

            assertNotNull(result);
            assertEquals(scheduleResponseDTO.id(), result.id());
            assertEquals(scheduleResponseDTO.firstTake(), result.firstTake());
            assertEquals(scheduleResponseDTO.timesPerDay(), result.timesPerDay());
            assertEquals(scheduleResponseDTO.medicineId(), result.medicineId());

            verify(scheduleMapper, times(1)).toEntity(scheduleRequestDTO);
            verify(repository, times(1)).save(scheduleEntity);
            verify(scheduleMapper, times(1)).toDTO(scheduleEntity);
        }
    }

    @Nested
    @DisplayName("Get by ID")
    class GetByIdTests {

        @Test
        @DisplayName("When schedule exists, it should return schedule")
        void getById_whenScheduleExists_shouldReturnSchedule() {
            Long id = 1L;
            when(repository.findById(id)).thenReturn(Optional.of(scheduleEntity));
            when(scheduleMapper.toDTO(scheduleEntity)).thenReturn(scheduleResponseDTO);

            ScheduleResponseDTO result = scheduleService.getById(id);

            assertNotNull(result);
            assertEquals(scheduleResponseDTO.id(), result.id());
            assertEquals(scheduleResponseDTO.firstTake(), result.firstTake());
            assertEquals(scheduleResponseDTO.timesPerDay(), result.timesPerDay());
            assertEquals(scheduleResponseDTO.medicineId(), result.medicineId());

            verify(repository, times(1)).findById(id);
            verify(scheduleMapper, times(1)).toDTO(scheduleEntity);
        }

        @Test
        @DisplayName("When schedule does not exist, it should throw RuntimeException")
        void getById_whenScheduleDoesNotExist_shouldThrowRuntimeException() {
            Long id = 999L;
            when(repository.findById(id)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> scheduleService.getById(id));

            assertEquals("Programación no encontrada. Id " + id + " no existe.", exception.getMessage());

            verify(repository, times(1)).findById(id);
            verifyNoInteractions(scheduleMapper);
        }
    }

    @Nested
    @DisplayName("Update entity")
    class UpdateEntityTests {

        @Test
        @DisplayName("When schedule exists, it should update schedule successfully")
        void updateEntity_whenScheduleExists_shouldUpdateScheduleSuccessfully() {
            Long id = 1L;
            ScheduleRequestDTO updateRequest = new ScheduleRequestDTO(
                    LocalDateTime.of(2024, 1, 15, 10, 0),
                    4,
                    1L
            );

            ScheduleEntity updatedEntity = ScheduleEntity.builder()
                    .id(id)
                    .firstTake(LocalDateTime.of(2024, 1, 15, 10, 0))
                    .timesPerDay(4)
                    .medicineId(medicineEntity)
                    .build();

            ScheduleResponseDTO updatedResponseDTO = new ScheduleResponseDTO(
                    id,
                    LocalDateTime.of(2024, 1, 15, 10, 0),
                    4,
                    1L
            );

            when(repository.findById(id)).thenReturn(Optional.of(scheduleEntity));
            when(repository.save(any(ScheduleEntity.class))).thenReturn(updatedEntity);
            when(scheduleMapper.toDTO(updatedEntity)).thenReturn(updatedResponseDTO);

            ScheduleResponseDTO result = scheduleService.updateEntity(id, updateRequest);

            assertNotNull(result);
            assertEquals(updatedResponseDTO.id(), result.id());
            assertEquals(updatedResponseDTO.firstTake(), result.firstTake());
            assertEquals(updatedResponseDTO.timesPerDay(), result.timesPerDay());
            assertEquals(updatedResponseDTO.medicineId(), result.medicineId());

            verify(repository, times(1)).findById(id);
            verify(repository, times(1)).save(any(ScheduleEntity.class));
            verify(scheduleMapper, times(1)).toDTO(updatedEntity);
        }

        @Test
        @DisplayName("When schedule does not exist, it should throw RuntimeException")
        void updateEntity_whenScheduleDoesNotExist_shouldThrowRuntimeException() {
            Long id = 999L;
            when(repository.findById(id)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> scheduleService.updateEntity(id, scheduleRequestDTO));

            assertEquals("Programación no encontrada con id: " + id, exception.getMessage());

            verify(repository, times(1)).findById(id);
            verify(repository, never()).save(any(ScheduleEntity.class));
            verifyNoInteractions(scheduleMapper);
        }
    }

    @Nested
    @DisplayName("Delete entity")
    class DeleteEntityTests {

        @Test
        @DisplayName("When schedule exists, it should delete schedule successfully")
        void deleteEntity_whenScheduleExists_shouldDeleteScheduleSuccessfully() {
            Long id = 1L;
            when(repository.findById(id)).thenReturn(Optional.of(scheduleEntity));

            scheduleService.deleteEntity(id);

            verify(repository, times(1)).findById(id);
            verify(repository, times(1)).delete(scheduleEntity);
        }

        @Test
        @DisplayName("When schedule does not exist, it should throw RuntimeException")
        void deleteEntity_whenScheduleDoesNotExist_shouldThrowRuntimeException() {
            Long id = 999L;
            when(repository.findById(id)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> scheduleService.deleteEntity(id));

            assertEquals("Programación no encontrada con id: " + id, exception.getMessage());

            verify(repository, times(1)).findById(id);
            verify(repository, never()).delete(any(ScheduleEntity.class));
        }
    }
}