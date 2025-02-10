package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingOrDescripcionContaining(String nombre, String descripcion);
    Optional<Producto> findByCodigo(String codigo);
    List<Producto> findByEmpresa_IdEmpresaAndEstado(Long empresaId, Integer estado);
    List<Producto> findByCategoria_IdCategoria(Long categoriaId);
    boolean existsByCodigo(String codigo);

    List<Producto> findByEmpresa_IdEmpresa(Long empresaId);
}