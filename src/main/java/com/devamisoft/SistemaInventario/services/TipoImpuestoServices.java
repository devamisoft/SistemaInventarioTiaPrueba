package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.TipoImpuestoDTO;
import com.devamisoft.SistemaInventario.dtos.Response;

public interface TipoImpuestoServices {
    Response createTipoImpuesto(TipoImpuestoDTO tipoImpuestoDTO);
    Response getAllTipoImpuestos();
    Response getTipoImpuestoById(Long id);
    Response updateTipoImpuesto(Long id, TipoImpuestoDTO tipoImpuestoDTO);
    Response deleteTipoImpuesto(Long id);
}