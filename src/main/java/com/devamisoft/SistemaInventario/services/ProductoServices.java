package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.ProductoDTO;
import com.devamisoft.SistemaInventario.dtos.Response;

public interface ProductoServices {
    Response createProducto(ProductoDTO productoDTO);
    Response getAllProductos();
    Response getProductoById(Long id);
    Response updateProducto(Long id, ProductoDTO productoDTO);
    Response deleteProducto(Long id);
    Response getProductosByCategoria(Long categoriaId);
    Response getProductosByCodigo(String codigo);
    Response getProductosByEmpresa(Long empresaId);
}