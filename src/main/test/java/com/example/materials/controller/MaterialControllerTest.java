package com.example.material.controller;

import com.example.material.dto.MaterialDTO;
import com.example.material.service.MaterialService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MaterialController.class)
public class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialService materialService;

    @Test
    void testGetAllMaterials_ShouldReturnOk() throws Exception {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(1L);
        dto.setNombre("Laptop");
        dto.setDescripcion("Dell XPS 13");
        dto.setTipo("Tecnología");
        dto.setPrecio(BigDecimal.valueOf(2000));
        dto.setFechaCompra(LocalDate.of(2023, 1, 1));
        dto.setEstado("disponible");
        dto.setCiudadId(1L);

        when(materialService.getAll()).thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/api/materiales")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Laptop"));
    }
}
