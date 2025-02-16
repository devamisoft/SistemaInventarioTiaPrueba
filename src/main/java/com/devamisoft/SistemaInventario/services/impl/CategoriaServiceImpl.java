package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.CategoriaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.Categoria;
import com.devamisoft.SistemaInventario.repositories.CategoriaRepository;
import com.devamisoft.SistemaInventario.services.CategoriaServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaServices {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaRepository.existsByNombreIgnoreCase(categoriaDTO.getNombre())) {
            return Response.builder()
                    .status(400)
                    .message("Ya existe una categoría con este nombre")
                    .build();
        }
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        categoriaRepository.save(categoria);

        return Response.builder()
                .status(200)
                .message("Categoría creada exitosamente")
                .categoria(modelMapper.map(categoria, CategoriaDTO.class))
                .build();
    }

    @Override
    public Response getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriasDTO = modelMapper.map(categorias,
                new TypeToken<List<CategoriaDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .categorias(categoriasDTO)
                .build();
    }

    @Override
    public Response getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .categoria(modelMapper.map(categoria, CategoriaDTO.class))
                .build();
    }

    @Override
    public Response updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        modelMapper.map(categoriaDTO, categoria);
        categoria.setIdCategoria(id);
        categoriaRepository.save(categoria);

        return Response.builder()
                .status(200)
                .message("Categoría actualizada exitosamente")
                .categoria(modelMapper.map(categoria, CategoriaDTO.class))
                .build();
    }

    @Override
    public Response deleteCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        if (categoriaRepository.existsByIdCategoriaAndProductosIsNotEmpty(id)) {
            // Borrado lógico si hay productos
            categoria.setEstado(0);
            categoriaRepository.save(categoria);

            return Response.builder()
                    .status(200)
                    .message("Categoría desactivada porque tiene productos asociados")
                    .build();
        }

        categoriaRepository.delete(categoria);

        return Response.builder()
                .status(200)
                .message("Categoría eliminada exitosamente")
                .build();
    }

    @Override
    public Response getCategoriasByEstado(Integer estado) {
        List<Categoria> categorias = categoriaRepository.findByEstado(estado);
        List<CategoriaDTO> categoriasDTO = modelMapper.map(categorias,
                new TypeToken<List<CategoriaDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .categorias(categoriasDTO)
                .build();
    }
}