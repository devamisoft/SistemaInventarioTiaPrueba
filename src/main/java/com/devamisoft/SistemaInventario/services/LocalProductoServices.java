package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.LocalProductoDTO;
import com.devamisoft.SistemaInventario.dtos.Response;

public interface LocalProductoServices {
    Response asignarStock(LocalProductoDTO request);
    Response actualizarStock(LocalProductoDTO request);
    Response getStockPorLocal(Long localId);
    Response getStockProducto(Long localId, Long productoId);
}