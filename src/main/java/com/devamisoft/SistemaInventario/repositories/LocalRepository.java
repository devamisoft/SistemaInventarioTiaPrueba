package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findByEmpresa_IdEmpresaAndEstado(Long empresaId, Integer estado);
    Optional<Local> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
}