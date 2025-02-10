package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByVenta_IdVenta(Long ventaId);
    List<DetalleVenta> findByProducto_IdProducto(Long productoId);
}