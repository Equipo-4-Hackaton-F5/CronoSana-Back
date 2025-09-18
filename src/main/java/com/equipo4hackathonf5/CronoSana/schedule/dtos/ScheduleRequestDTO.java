package com.equipo4hackathonf5.CronoSana.schedule.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ScheduleRequestDTO (
    @NotNull(message = "The date and time of the first intake is mandatory")
    @Future(message = "The first intake must be in the future")
        LocalDateTime firstTake,

    @NotNull(message = "The number of doses per day is required")
    @Size(min = 1, max = 12, message = "The must be at least one dose and at most 12 doses per day")
    Integer timesPerDay,

    @NotNull(message = "The medicine id is mandatory")
    Long medicineId
){
}
