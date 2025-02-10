package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.VentaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;

public interface VentaServices {
    Response createVenta(VentaDTO ventaDTO);
    Response getAllVentas();
    Response getVentaById(Long id);
    Response updateVenta(Long id, VentaDTO ventaDTO);
    Response deleteVenta(Long id);
    Response getVentasByLocal(Long localId);
    Response getVentasByUsuario(Long usuarioId);
}