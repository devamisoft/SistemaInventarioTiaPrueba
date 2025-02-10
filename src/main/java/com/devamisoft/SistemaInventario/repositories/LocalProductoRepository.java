package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.LocalProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalProductoRepository extends JpaRepository<LocalProducto, Long> {
    List<LocalProducto> findByLocal_IdLocalesAndProducto_IdProducto(Long localId, Long productoId);
    List<LocalProducto> findByLocal_IdLocales(Long localId);
    List<LocalProducto> findByProducto_IdProducto(Long productoId);
}