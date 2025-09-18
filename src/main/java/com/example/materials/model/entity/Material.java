package com.example.materials.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa un material en el sistema.
 * Se persiste en la base de datos utilizando JPA.
 *
 * Campos:
 * - id: Identificador único.
 * - nombre: Nombre del material.
 * - descripcion: Descripción del material.
 * - tipo: Categoría del material (ej: electrónico, mueble, etc).
 * - precio: Precio del material.
 * - fechaCompra: Fecha en que fue adquirido.
 * - fechaVenta: Fecha en que fue vendido (si aplica).
 * - estado: Estado del material (activo, disponible, asignado).
 * - ciudad: Relación con la ciudad donde se encuentra.
 *
 * Restricciones:
 * - La fecha de compra no puede ser mayor que la fecha de venta.
 */

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Column(length = 1000)
    private String descripcion;
    private String tipo;
    private BigDecimal precio;
    private LocalDate fechaCompra;
    private LocalDate fechaVenta;
    private String estado; // activo | disponible | asignado

    @ManyToOne(optional = false)
    @JoinColumn(name = "ciudad_codigo")
    private Ciudad ciudad;

    // getters y setters
}