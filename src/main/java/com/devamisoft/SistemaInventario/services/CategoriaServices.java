package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.CategoriaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;

public interface CategoriaServices {
    Response createCategoria(CategoriaDTO categoriaDTO);
    Response getAllCategorias();
    Response getCategoriaById(Long id);
    Response updateCategoria(Long id, CategoriaDTO categoriaDTO);
    Response deleteCategoria(Long id);
    Response getCategoriasByEstado(Integer estado);
}