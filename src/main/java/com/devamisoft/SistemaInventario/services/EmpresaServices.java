package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.EmpresaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;

public interface EmpresaServices {
    Response createEmpresa(EmpresaDTO empresaDTO);
    Response getAllEmpresas();
    Response getEmpresaById(Long id);
    Response updateEmpresa(Long id, EmpresaDTO empresaDTO);
    Response deleteEmpresa(Long id);
    Response getEmpresaByRuc(String ruc);
}