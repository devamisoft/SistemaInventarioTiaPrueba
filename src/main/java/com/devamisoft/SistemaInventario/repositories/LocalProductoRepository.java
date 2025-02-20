package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.LocalProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalProductoRepository extends JpaRepository<LocalProducto, Long> {

    Optional<LocalProducto> findByLocal_IdLocalesAndProducto_IdProducto(Long localId, Long productoId);

    List<LocalProducto> findByLocal_IdLocales(Long localId);

    List<LocalProducto> findByProducto_IdProducto(Long productoId);

    @Query("SELECT lp FROM LocalProducto lp WHERE lp.local.idLocales = :localId AND lp.producto.idProducto = :productoId")
    Optional<LocalProducto> findStockByLocalAndProducto(
            @Param("localId") Long localId,
            @Param("productoId") Long productoId
    );

    @Query("SELECT lp FROM LocalProducto lp WHERE lp.local.idLocales = :localId AND lp.stock > 0")
    List<LocalProducto> findStockDisponibleByLocal(@Param("localId") Long localId);
}
