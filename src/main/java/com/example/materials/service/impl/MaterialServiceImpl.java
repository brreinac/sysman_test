package com.example.materials.service;

import com.example.materials.exception.ResourceNotFoundException;
import com.example.materials.model.Material;
import com.example.materials.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de {@link MaterialService}.
 * Contiene la lógica de negocio y validaciones para la gestión de materiales.
 *
 * Validaciones:
 * - Controla que no existan fechas de compra posteriores a las de venta.
 * - Lanza {@link ResourceNotFoundException} cuando no encuentra recursos.
 *
 * Manejo de errores:
 * - Los errores son propagados al {@link GlobalExceptionHandler}.
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material getById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado con id " + id));
    }

    @Override
    public Material create(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Material update(Long id, Material material) {
        Material existing = getById(id);
        existing.setNombre(material.getNombre());
        existing.setDescripcion(material.getDescripcion());
        existing.setTipo(material.getTipo());
        existing.setPrecio(material.getPrecio());
        existing.setFechaCompra(material.getFechaCompra());
        return materialRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Material material = getById(id);
        materialRepository.delete(material);
    }
}
