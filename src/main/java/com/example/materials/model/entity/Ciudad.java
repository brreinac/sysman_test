package com.example.materials.model.entity;

import jakarta.persistence.*;

@Entity
public class Ciudad {
    @Id
    private String codigo;
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "departamento_codigo")
    private Departamento departamento;

    // getters y setters
}