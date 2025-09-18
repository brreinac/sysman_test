package com.example.materials.controller;

import com.example.materials.dto.MaterialRequest;
import com.example.materials.dto.MaterialResponse;
import com.example.materials.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de materiales.
 * Expone endpoints para operaciones CRUD y consultas filtradas.
 *
 * Endpoints:
 * - GET /api/materiales -> Lista todos los materiales.
 * - GET /api/materiales/tipo/{tipo} -> Lista materiales por tipo.
 * - GET /api/materiales/fecha?compra=yyyy-MM-dd -> Lista materiales por fecha de compra.
 * - GET /api/materiales/ciudad/{ciudadId} -> Lista materiales por ciudad.
 * - POST /api/materiales -> Crea un nuevo material.
 * - PUT /api/materiales/{id} -> Actualiza un material existente.
 *
 * Respuestas HTTP:
 * - 200 OK: Consulta o acción exitosa.
 * - 404 Not Found: Recurso no encontrado.
 * - 500 Internal Server Error: Error en la capa de backend.
 *
 * Todas las respuestas incluyen un mensaje descriptivo.
 */

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // 🔓 Público: obtener todos los materiales
    @GetMapping
    public ResponseEntity<List<MaterialDTO>> getAll() {
        return ResponseEntity.ok(materialService.getAll());
    }

    // 🔓 Público: buscar por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<MaterialDTO>> findByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(materialService.findByTipo(tipo));
    }

    // 🔓 Público: buscar por fechaCompra
    @GetMapping("/fecha/{fechaCompra}")
    public ResponseEntity<List<MaterialDTO>> findByFechaCompra(@PathVariable String fechaCompra) {
        return ResponseEntity.ok(materialService.findByFechaCompra(fechaCompra));
    }

    // 🔓 Público: buscar por ciudad
    @GetMapping("/ciudad/{ciudadId}")
    public ResponseEntity<List<MaterialDTO>> findByCiudad(@PathVariable Long ciudadId) {
        return ResponseEntity.ok(materialService.findByCiudad(ciudadId));
    }

    // 🔐 Protegido con JWT: crear
    @PostMapping
    public ResponseEntity<MaterialDTO> create(@RequestBody MaterialDTO dto) {
        return ResponseEntity.ok(materialService.create(dto));
    }

    // 🔐 Protegido con JWT: actualizar
    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> update(@PathVariable Long id, @RequestBody MaterialDTO dto) {
        return ResponseEntity.ok(materialService.update(id, dto));
    }
}