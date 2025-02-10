package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.TipoImpuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TipoImpuestoRepository extends JpaRepository<TipoImpuesto, Long> {
    List<TipoImpuesto> findByEstado(Integer estado);
    Optional<TipoImpuesto> findByNombre(String nombre);
}