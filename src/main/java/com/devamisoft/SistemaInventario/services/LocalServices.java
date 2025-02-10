package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.LocalDTO;
import com.devamisoft.SistemaInventario.dtos.Response;

public interface LocalServices {
    Response createLocal(LocalDTO localDTO);
    Response getAllLocales();
    Response getLocalById(Long id);
    Response updateLocal(Long id, LocalDTO localDTO);
    Response deleteLocal(Long id);
    Response getLocalesByEmpresa(Long empresaId);
    Response getLocalByCodigo(String codigo);
}
