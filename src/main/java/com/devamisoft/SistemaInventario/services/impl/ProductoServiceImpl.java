package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.ProductoDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.Categoria;
import com.devamisoft.SistemaInventario.models.Empresa;
import com.devamisoft.SistemaInventario.models.Producto;
import com.devamisoft.SistemaInventario.models.TipoImpuesto;
import com.devamisoft.SistemaInventario.repositories.*;
import com.devamisoft.SistemaInventario.services.ProductoServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoServices {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EmpresaRepository empresaRepository;
    private final TipoImpuestoRepository tipoImpuestoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createProducto(ProductoDTO productoDTO) {
        Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        Empresa empresa = empresaRepository.findById(productoDTO.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));

        TipoImpuesto tipoImpuesto = tipoImpuestoRepository.findById(productoDTO.getImpuestoId())
                .orElseThrow(() -> new NotFoundException("Tipo de impuesto no encontrado"));

        Producto producto = modelMapper.map(productoDTO, Producto.class);
        producto.setCategoria(categoria);
        producto.setEmpresa(empresa);
        producto.setTipoImpuesto(tipoImpuesto);

        productoRepository.save(producto);

        return Response.builder()
                .status(200)
                .message("Producto creado exitosamente")
                .producto(modelMapper.map(producto, ProductoDTO.class))
                .build();
    }

    @Override
    public Response getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = modelMapper.map(productos,
                new TypeToken<List<ProductoDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .productos(productosDTO)
                .build();
    }

    @Override
    public Response getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .producto(modelMapper.map(producto, ProductoDTO.class))
                .build();
    }

    @Override
    public Response updateProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
            producto.setCategoria(categoria);
        }

        if (productoDTO.getImpuestoId() != null) {
            TipoImpuesto tipoImpuesto = tipoImpuestoRepository.findById(productoDTO.getImpuestoId())
                    .orElseThrow(() -> new NotFoundException("Tipo de impuesto no encontrado"));
            producto.setTipoImpuesto(tipoImpuesto);
        }

        modelMapper.map(productoDTO, producto);
        producto.setIdProducto(id);
        productoRepository.save(producto);

        return Response.builder()
                .status(200)
                .message("Producto actualizado exitosamente")
                .producto(modelMapper.map(producto, ProductoDTO.class))
                .build();
    }

    @Override
    public Response deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Producto eliminado exitosamente")
                .build();
    }

    @Override
    public Response getProductosByCategoria(Long categoriaId) {
        List<Producto> productos = productoRepository.findByCategoria_IdCategoria(categoriaId);
        List<ProductoDTO> productosDTO = modelMapper.map(productos,
                new TypeToken<List<ProductoDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .productos(productosDTO)
                .build();
    }

    @Override
    public Response getProductosByCodigo(String codigo) {
        Producto producto = productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .producto(modelMapper.map(producto, ProductoDTO.class))
                .build();
    }

    @Override
    public Response getProductosByEmpresa(Long empresaId) {
        List<Producto> productos = productoRepository.findByEmpresa_IdEmpresa(empresaId);
        List<ProductoDTO> productosDTO = modelMapper.map(productos,
                new TypeToken<List<ProductoDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .productos(productosDTO)
                .build();
    }
}