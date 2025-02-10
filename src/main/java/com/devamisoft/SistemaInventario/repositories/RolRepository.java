package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
    List<Rol> findByEstado(Integer estado);
}