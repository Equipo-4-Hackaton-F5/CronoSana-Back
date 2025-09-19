package com.equipo4hackathonf5.CronoSana.medicine;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineRequestDTO;
import com.equipo4hackathonf5.CronoSana.medicine.dtos.MedicineResponseDTO;


@RunWith(MockitoJUnitRunner.class)
public class MedicineServiceImplTest {

    @Mock
    private MedicineRepository repository;

    @InjectMocks
    private MedicineServiceImpl service;

    @Test
    public void testGetEntities() {
        MedicineEntity entity1 = new MedicineEntity(1L, "Paracetamol", "500mg");
        MedicineEntity entity2 = new MedicineEntity(2L, "Ibuprofeno", "400mg");
        when(repository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        List<MedicineResponseDTO> result = service.getEntities();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Paracetamol", result.get(0).name());
        assertEquals("Ibuprofeno", result.get(1).name());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testCreateEntity() {
        MedicineRequestDTO requestDTO = new MedicineRequestDTO("Amoxicilina", "500mg");
        MedicineEntity savedEntity = new MedicineEntity(1L, "Amoxicilina", "500mg");

        when(repository.save(any(MedicineEntity.class))).thenReturn(savedEntity);

        MedicineResponseDTO result = service.createEntity(requestDTO);

        assertNotNull(result);
        assertEquals("Amoxicilina", result.name());
        assertEquals("500mg", result.dose());
        verify(repository, times(1)).save(any(MedicineEntity.class));
    }

    @Test
    public void testGetById_found() {
        Long id = 1L;
        MedicineEntity entity = new MedicineEntity(id, "Paracetamol", "500mg");
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        MedicineResponseDTO result = service.getById(id);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Paracetamol", result.name());
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testGetById_notFound() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testUpdateEntity_found() {
        Long id = 1L;
        MedicineRequestDTO requestDTO = new MedicineRequestDTO("Paracetamol", "650mg");
        MedicineEntity existingEntity = new MedicineEntity(id, "Paracetamol", "500mg");

        when(repository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(repository.save(any(MedicineEntity.class))).thenReturn(existingEntity);

        MedicineResponseDTO result = service.updateEntity(id, requestDTO);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Paracetamol", result.name());
        assertEquals("650mg", result.dose());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(existingEntity);
    }

    @Test
    public void testDeleteEntity_found() {
        Long id = 1L;
        MedicineEntity entity = new MedicineEntity(id, "Paracetamol", "500mg");
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(repository).delete(entity);

        service.deleteEntity(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(entity);
    }

    @Test
    public void testDeleteEntity_notFound() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.deleteEntity(id));
        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any());
    }
}
