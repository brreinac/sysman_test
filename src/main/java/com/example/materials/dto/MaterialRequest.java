package com.example.materials.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class MaterialRequest {
    @NotBlank
    private String nombre;
    private String descripcion;
    @NotBlank
    private String tipo;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precio;
    @NotNull
    private LocalDate fechaCompra;
    private LocalDate fechaVenta;
    @Pattern(regexp = "activo|disponible|asignado")
    private String estado;
    @NotBlank
    private String ciudadCodigo;

    // getters y setters
}