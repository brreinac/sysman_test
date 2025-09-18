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

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<MaterialDTO> getAll() {
        return materialRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDTO> findByTipo(String tipo) {
        return materialRepository.findByTipo(tipo)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDTO> findByFechaCompra(String fechaCompra) {
        LocalDate date = LocalDate.parse(fechaCompra);
        return materialRepository.findByFechaCompra(date)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDTO> findByCiudad(Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada"));
        return materialRepository.findByCiudad(ciudad)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaterialDTO create(MaterialDTO dto) {
        validateFechas(dto);
        Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
                .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada"));

        Material material = new Material();
        material.setNombre(dto.getNombre());
        material.setDescripcion(dto.getDescripcion());
        material.setTipo(dto.getTipo());
        material.setPrecio(dto.getPrecio());
        material.setFechaCompra(dto.getFechaCompra());
        material.setFechaVenta(dto.getFechaVenta());
        material.setEstado(dto.getEstado());
        material.setCiudad(ciudad);

        return mapToDto(materialRepository.save(material));
    }

    @Override
    public MaterialDTO update(Long id, MaterialDTO dto) {
        validateFechas(dto);

        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));

        Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
                .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada"));

        material.setNombre(dto.getNombre());
        material.setDescripcion(dto.getDescripcion());
        material.setTipo(dto.getTipo());
        material.setPrecio(dto.getPrecio());
        material.setFechaCompra(dto.getFechaCompra());
        material.setFechaVenta(dto.getFechaVenta());
        material.setEstado(dto.getEstado());
        material.setCiudad(ciudad);

        return mapToDto(materialRepository.save(material));
    }

    private void validateFechas(MaterialDTO dto) {
        if (dto.getFechaVenta() != null && dto.getFechaCompra().isAfter(dto.getFechaVenta())) {
            throw new IllegalArgumentException("La fecha de compra no puede ser posterior a la fecha de venta.");
        }
    }

    private MaterialDTO mapToDto(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(material.getId());
        dto.setNombre(material.getNombre());
        dto.setDescripcion(material.getDescripcion());
        dto.setTipo(material.getTipo());
        dto.setPrecio(material.getPrecio());
        dto.setFechaCompra(material.getFechaCompra());
        dto.setFechaVenta(material.getFechaVenta());
        dto.setEstado(material.getEstado());
        dto.setCiudadId(material.getCiudad().getId());
        return dto;
    }
}
