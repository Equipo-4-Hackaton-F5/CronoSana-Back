package com.equipo4hackathonf5.CronoSana.schedule;

import com.equipo4hackathonf5.CronoSana.schedule.dtos.ScheduleRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("ScheduleController Integration Tests")
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("Get all schedules")
    class GetAllSchedules {

        @Test
        @DisplayName("Should return 200 when getting all schedules")
        void getAllSchedules_whenValidRequest_shouldReturnOk() throws Exception {
            mockMvc.perform(get("/api/schedules")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Should return list of schedules with correct structure")
        void getAllSchedules_whenSchedulesExist_shouldReturnListWithCorrectStructure() throws Exception {
            mockMvc.perform(get("/api/schedules")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray());
        }
    }

    @Nested
    @DisplayName("Get schedule by ID")
    class GetScheduleById {

        @Test
        @DisplayName("Should return 200 when schedule exists")
        void getScheduleById_whenScheduleExists_shouldReturnOk() throws Exception {
            mockMvc.perform(get("/api/schedules/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstTake").exists())
                    .andExpect(jsonPath("$.timesPerDay").exists())
                    .andExpect(jsonPath("$.medicineId").exists());
        }
    }

    @Nested
    @DisplayName("Create schedule")
    class CreateSchedule {

        @Test
        @DisplayName("Should return 201 when creating schedule with valid data")
        void createSchedule_whenValidData_shouldReturnCreated() throws Exception {
            ScheduleRequestDTO scheduleRequest = new ScheduleRequestDTO(
                    LocalDateTime.of(2025, 12, 31, 8, 0, 0),
                    3,
                    3L
            );

            mockMvc.perform(post("/api/schedules")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(scheduleRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstTake").exists())
                    .andExpect(jsonPath("$.timesPerDay").value(3))
                    .andExpect(jsonPath("$.medicineId").value(3));
        }
    }

    @Nested
    @DisplayName("Update schedule")
    class UpdateSchedule {

        @Test
        @DisplayName("Should return 200 when updating schedule with valid data")
        void updateSchedule_whenValidData_shouldReturnOk() throws Exception {
            ScheduleRequestDTO updateRequest = new ScheduleRequestDTO(
                    LocalDateTime.of(2025, 12, 31, 10, 0),
                    4,
                    1L
            );

            mockMvc.perform(put("/api/schedules/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(updateRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.timesPerDay").value(4));
        }
    }

    @Nested
    @DisplayName("Delete schedule")
    class DeleteSchedule {

        @Test
        @DisplayName("Should return 204 when deleting existing schedule")
        void deleteSchedule_whenScheduleExists_shouldReturnNoContent() throws Exception {
            mockMvc.perform(delete("/api/schedules/1"))
                    .andExpect(status().isNoContent());
        }
    }
}