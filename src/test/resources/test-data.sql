-- Deshabilitar restricciones de integridad referencial
SET REFERENTIAL_INTEGRITY FALSE;

-- Limpiar datos existentes con DELETE (más seguro para FK)
DELETE FROM schedule;
DELETE FROM medicine;

-- Habilitar restricciones nuevamente
SET REFERENTIAL_INTEGRITY TRUE;

-- Insertar datos de prueba para medicamentos
INSERT INTO medicine (id, name, dose) VALUES
(1, 'Paracetamol', '500mg'),
(2, 'Ibuprofeno', '400mg'),
(3, 'Aspirina', '100mg');

-- Insertar datos de prueba para programaciones
INSERT INTO schedule (id, first_take, times_per_day, medicine_id) VALUES
(1, '2024-12-01 08:00:00', 3, 1),
(2, '2024-12-01 09:00:00', 2, 2);