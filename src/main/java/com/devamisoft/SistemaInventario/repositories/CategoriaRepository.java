package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNombreContaining(String nombre);
    List<Categoria> findByEstado(Integer estado);
    boolean existsByNombre(String nombre);
}
