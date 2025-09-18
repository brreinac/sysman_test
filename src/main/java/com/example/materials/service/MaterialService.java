package com.example.materials.service;

import com.example.materials.dto.MaterialRequest;
import com.example.materials.dto.MaterialResponse;
import java.time.LocalDate;
import java.util.List;

public interface MaterialService {
    List<MaterialResponse> getAll();
    List<MaterialResponse> findByTipo(String tipo);
    List<MaterialResponse> findByFechaCompra(LocalDate fecha);
    List<MaterialResponse> findByCiudad(String ciudadCodigo);
    MaterialResponse create(MaterialRequest req);
    MaterialResponse update(Long id, MaterialRequest req);
}