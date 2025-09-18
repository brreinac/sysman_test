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
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    private ResponseEntity<Map<String,Object>> wrap(int status, String message, Object data){
        return ResponseEntity.status(status).body(Map.of(
            "status", status,
            "message", message,
            "data", data
        ));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<MaterialResponse> list = materialService.getAll();
        if(list.isEmpty()) return wrap(404, "No materials found", List.of());
        return wrap(200, "Ok", list);
    }

    @GetMapping("/search/byTipo")
    public ResponseEntity<?> byTipo(@RequestParam String tipo){
        List<MaterialResponse> list = materialService.findByTipo(tipo);
        if(list.isEmpty()) return wrap(404, "No materials for tipo="+tipo, List.of());
        return wrap(200, "Ok", list);
    }

    @GetMapping("/search/byFechaCompra")
    public ResponseEntity<?> byFecha(@RequestParam String fecha){
        LocalDate d = LocalDate.parse(fecha);
        List<MaterialResponse> list = materialService.findByFechaCompra(d);
        if(list.isEmpty()) return wrap(404, "No materials for fechaCompra="+fecha, List.of());
        return wrap(200, "Ok", list);
    }

    @GetMapping("/search/byCiudad")
    public ResponseEntity<?> byCiudad(@RequestParam String ciudadCodigo){
        List<MaterialResponse> list = materialService.findByCiudad(ciudadCodigo);
        if(list.isEmpty()) return wrap(404, "No materials for ciudad="+ciudadCodigo, List.of());
        return wrap(200, "Ok", list);
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody MaterialRequest req){
        MaterialResponse res = materialService.create(req);
        return wrap(200, "Created", res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody MaterialRequest req){
        MaterialResponse res = materialService.update(id, req);
        return wrap(200, "Updated", res);
    }
}