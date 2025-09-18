package com.example.materials.model.entity;

import jakarta.persistence.*;

/**
 * Data Transfer Object (DTO) para {@link Material}.
 * Se utiliza en las peticiones y respuestas de la API.
 *
 * Campos:
 * - id: Identificador del material.
 * - nombre: Nombre del material.
 * - descripcion: Descripción del material.
 * - tipo: Tipo de material.
 * - precio: Precio asociado.
 * - fechaCompra: Fecha de adquisición.
 * - fechaVenta: Fecha de venta (opcional).
 * - estado: Estado actual (activo, disponible, asignado).
 * - ciudadId: Relación con la ciudad correspondiente.
 *
 * Uso:
 * Permite aislar la representación de datos expuestos en la API
 * de la entidad persistente {@link Material}.
 */

@Entity
public class Departamento {
    @Id
    private String codigo;
    private String nombre;

    // getters y setters
}