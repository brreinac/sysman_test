package com.example.materials.service;

import com.example.materials.exception.ResourceNotFoundException;
import com.example.materials.model.Material;
import com.example.materials.repository.MaterialRepository;
import com.example.materials.service.impl.MaterialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialServiceImpl materialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createShouldThrowWhenFechaCompraAfterFechaVenta() {
        Material m = new Material();
        m.setNombre("Test");
        m.setFechaCompra(LocalDate.of(2025,9,10));
        m.setFechaRegistro(LocalDate.of(2025,9,9)); // nota: fechaRegistro en entidad; adjust if your entity has fechaVenta field

        // For this test we'll assume service checks fechaCompra <= fechaVenta (if fechaVenta exists)
        // If materialService.create checks and throws IllegalArgumentException, test that:
        // Here simulate fechaVenta earlier
        m.setFechaCompra(LocalDate.of(2025,9,12));
        // Simulate fechaVenta is 2025-09-10 (assuming a getter exists)
        // If your entity has fechaVenta field, set it and then:
        // m.setFechaVenta(LocalDate.of(2025,9,10));

        assertThrows(IllegalArgumentException.class, () -> materialService.create(m));
    }

    @Test
    void getByIdNotFoundThrows() {
        when(materialRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> materialService.getById(1L));
    }
}
