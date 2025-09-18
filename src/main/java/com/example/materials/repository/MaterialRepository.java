package com.example.materials.repository;

import com.example.materials.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio JPA para la entidad {@link Material}.
 * Provee métodos CRUD y consultas personalizadas.
 *
 * Métodos disponibles:
 * - findAll() -> Retorna todos los materiales.
 * - findByTipo(String tipo) -> Busca materiales por tipo.
 * - findByFechaCompra(LocalDate fechaCompra) -> Busca materiales por fecha de compra.
 * - findByCiudadId(Long ciudadId) -> Busca materiales por ciudad.
 *
 * Uso:
 * Es invocado desde la capa de servicio {@link MaterialService} para acceder a la base de datos.
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByTipo(String tipo);
    List<Material> findByFechaCompra(LocalDate fechaCompra);
    List<Material> findByCiudad_Codigo(String ciudadCodigo);
}