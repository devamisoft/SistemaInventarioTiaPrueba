package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByRuc(String ruc);
    List<Empresa> findByNombreContainingOrNombreComercialContaining(String nombre, String nombreComercial);
    boolean existsByRuc(String ruc);
}