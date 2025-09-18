/**
 * Repositorio JPA para la entidad {@link Ciudad}.
 * Permite gestionar la persistencia de ciudades.
 *
 * Métodos principales:
 * - findAll() -> Lista todas las ciudades.
 * - findByDepartamentoId(Long id) -> Busca ciudades por departamento.
 */
@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByDepartamentoId(Long departamentoId);
}
