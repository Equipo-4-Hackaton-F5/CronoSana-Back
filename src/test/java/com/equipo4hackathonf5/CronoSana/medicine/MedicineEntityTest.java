package com.equipo4hackathonf5.CronoSana.medicine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class MedicineEntityTest {
    @Test
    void testMedicineEntityGettersAndSetters() {
        Long expectedId = 1L;
        String expectedName = "Paracetamol";
        String expectedDose = "500mg";

        MedicineEntity medicine = new MedicineEntity();

        medicine.setId(expectedId);
        medicine.setName(expectedName);
        medicine.setDose(expectedDose);

        assertEquals(expectedId, medicine.getId());
        assertEquals(expectedName, medicine.getName());
        assertEquals(expectedDose, medicine.getDose());
    }

    @Test
    void testMedicineEntityBuilder() {
        MedicineEntity medicine = MedicineEntity.builder()
                                                 .id(2L)
                                                 .name("Ibuprofeno")
                                                 .dose("400mg")
                                                 .build();


        assertNotNull(medicine);
        assertEquals(2L, medicine.getId());
        assertEquals("Ibuprofeno", medicine.getName());
        assertEquals("400mg", medicine.getDose());
    }
}
