package com.example.material.service;

import com.example.material.dto.MaterialDTO;
import com.example.material.entity.Ciudad;
import com.example.material.entity.Material;
import com.example.material.exception.ResourceNotFoundException;
import com.example.material.repository.CiudadRepository;
import com.example.material.repository.MaterialRepository;
import com.example.material.service.impl.MaterialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private CiudadRepository ciudadRepository;

    @InjectMocks
    private MaterialServiceImpl materialService;

    private MaterialDTO materialDto;
    private Ciudad ciudad;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ciudad = new Ciudad();
        ciudad.setId(1L);
        ciudad.setNombre("Bogotá");

        materialDto = new MaterialDTO();
        materialDto.setNombre("Laptop");
        materialDto.setDescripcion("Dell XPS 13");
        materialDto.setTipo("Tecnología");
        materialDto.setPrecio(BigDecimal.valueOf(2000));
        materialDto.setFechaCompra(LocalDate.of(2023, 1, 1));
        materialDto.setFechaVenta(LocalDate.of(2023, 6, 1));
        materialDto.setEstado("disponible");
        materialDto.setCiudadId(1L);
    }

    @Test
    void testCreateMaterial_Success() {
        when(ciudadRepository.findById(1L)).thenReturn(Optional.of(ciudad));
        when(materialRepository.save(any(Material.class))).thenAnswer(i -> i.getArguments()[0]);

        MaterialDTO saved = materialService.create(materialDto);

        assertNotNull(saved);
        assertEquals("Laptop", saved.getNombre());
    }

    @Test
    void testCreateMaterial_InvalidDates_ShouldThrowException() {
        materialDto.setFechaCompra(LocalDate.of(2023, 7, 1));
        materialDto.setFechaVenta(LocalDate.of(2023, 6, 1));

        assertThrows(IllegalArgumentException.class, () -> materialService.create(materialDto));
    }

    @Test
    void testUpdateMaterial_NotFound_ShouldThrowException() {
        when(materialRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> materialService.update(1L, materialDto));
    }
}
