package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNombreContaining(String nombre);
    List<Categoria> findByEstado(Integer estado);
    boolean existsByNombre(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Categoria c JOIN c.productos p WHERE c.idCategoria = :categoriaId")
    boolean existsByIdCategoriaAndProductosIsNotEmpty(@Param("categoriaId") Long categoriaId);

}
