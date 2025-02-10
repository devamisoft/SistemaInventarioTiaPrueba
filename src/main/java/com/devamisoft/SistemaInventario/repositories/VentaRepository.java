package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByLocal_IdLocales(Long localId);
    List<Venta> findByLocal_IdLocalesAndEstado(Long localId, Integer estado);
    List<Venta> findByUsuario_IdUsuarios(Long usuarioId);
    List<Venta> findByLocal_IdLocalesAndFechaVentaBetween(Long localId, LocalDateTime inicio, LocalDateTime fin);
}