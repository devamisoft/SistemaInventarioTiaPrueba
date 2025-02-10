package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.InventarioMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InventarioMovimientoRepository extends JpaRepository<InventarioMovimiento, Long> {
    List<InventarioMovimiento> findByLocal_IdLocalesAndProducto_IdProducto(Long localId, Long productoId);
    List<InventarioMovimiento> findByTipoMovimiento_IdTipoMovimiento(Long tipoMovimientoId);
    List<InventarioMovimiento> findByLocal_IdLocalesAndCreatedAtBetween(Long localId, LocalDateTime inicio, LocalDateTime fin);
}