package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.DetalleVentaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;

public interface DetalleVentaServices {
    Response createDetalleVenta(DetalleVentaDTO detalleVentaDTO);
    Response getDetallesByVenta(Long ventaId);
    Response getDetalleVentaById(Long id);
    Response updateDetalleVenta(Long id, DetalleVentaDTO detalleVentaDTO);
    Response deleteDetalleVenta(Long id);
}