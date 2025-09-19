
package com.equipo4hackathonf5.CronoSana.medicine;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.equipo4hackathonf5.CronoSana.implementations.IService;
import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineRequestDTO;
import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class) // JUnit4 + Spring Boot
@WebMvcTest(MedicineController.class)
public class MedicineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IService<MedicineResponseDTO, MedicineRequestDTO> service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllMedicines() throws Exception {
        List<MedicineResponseDTO> medicineList = Arrays.asList(
                new MedicineResponseDTO(1L, "Paracetamol", "500mg"),
                new MedicineResponseDTO(2L, "Ibuprofeno", "400mg")
        );
        when(service.getEntities()).thenReturn(medicineList);

        mockMvc.perform(get("/api/medicines")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Paracetamol"));

        verify(service, times(1)).getEntities();
    }

    @Test
    public void testCreateMedicine() throws Exception {
        MedicineRequestDTO requestDTO = new MedicineRequestDTO("Amoxicilina", "500mg");
        MedicineResponseDTO responseDTO = new MedicineResponseDTO(3L, "Amoxicilina", "500mg");

        when(service.createEntity(any(MedicineRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/medicines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.name").value("Amoxicilina"));

        verify(service, times(1)).createEntity(any(MedicineRequestDTO.class));
    }

    @Test
    public void testGetMedicineById() throws Exception {
        Long medicineId = 1L;
        MedicineResponseDTO responseDTO = new MedicineResponseDTO(medicineId, "Paracetamol", "500mg");
        when(service.getById(medicineId)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/medicines/{id}", medicineId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(medicineId))
                .andExpect(jsonPath("$.name").value("Paracetamol"));

        verify(service, times(1)).getById(medicineId);
    }

    @Test
    public void testUpdateMedicine() throws Exception {
        Long medicineId = 1L;
        MedicineRequestDTO requestDTO = new MedicineRequestDTO("Paracetamol", "650mg");
        MedicineResponseDTO responseDTO = new MedicineResponseDTO(medicineId, "Paracetamol", "650mg");

        when(service.updateEntity(eq(medicineId), any(MedicineRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/medicines/{id}", medicineId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(medicineId))
                .andExpect(jsonPath("$.name").value("Paracetamol"))
                .andExpect(jsonPath("$.dose").value("650mg"));

        verify(service, times(1)).updateEntity(eq(medicineId), any(MedicineRequestDTO.class));
    }

    @Test
    public void testDeleteMedicine() throws Exception {
        Long medicineId = 1L;
        doNothing().when(service).deleteEntity(medicineId);

        mockMvc.perform(delete("/api/medicines/{id}", medicineId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteEntity(medicineId);
    }
}

