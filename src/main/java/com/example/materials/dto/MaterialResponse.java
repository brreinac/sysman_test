package com.example.materials.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MaterialResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private BigDecimal precio;
    private LocalDate fechaCompra;
    private LocalDate fechaVenta;
    private String estado;
    private String ciudadCodigo;
    private String ciudadNombre;
    private String departamentoCodigo;

    // getters y setters
}