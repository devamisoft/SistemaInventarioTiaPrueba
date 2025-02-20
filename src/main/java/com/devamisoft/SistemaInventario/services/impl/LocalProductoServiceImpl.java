package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.LocalProductoDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.Local;
import com.devamisoft.SistemaInventario.models.LocalProducto;
import com.devamisoft.SistemaInventario.models.Producto;
import com.devamisoft.SistemaInventario.repositories.LocalProductoRepository;
import com.devamisoft.SistemaInventario.repositories.LocalRepository;
import com.devamisoft.SistemaInventario.repositories.ProductoRepository;
import com.devamisoft.SistemaInventario.services.LocalProductoServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LocalProductoServiceImpl implements LocalProductoServices {

    private final LocalProductoRepository localProductoRepository;
    private final LocalRepository localRepository;
    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response asignarStock(LocalProductoDTO request) {
        Local local = localRepository.findById(request.getLocalId())
                .orElseThrow(() -> new NotFoundException("Local no encontrado"));

        Producto producto = productoRepository.findById(request.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        LocalProducto localProducto = localProductoRepository
                .findByLocalIdAndProductoId(request.getLocalId(), request.getProductoId())
                .orElse(new LocalProducto());

        localProducto.setLocal(local);
        localProducto.setProducto(producto);
        localProducto.setStock(request.getStock());

        localProductoRepository.save(localProducto);

        return Response.builder()
                .status(200)
                .message("Stock asignado exitosamente")
                .build();
    }

    @Override
    public Response actualizarStock(LocalProductoDTO request) {
        LocalProducto localProducto = localProductoRepository
                .findByLocalIdAndProductoId(request.getLocalId(), request.getProductoId())
                .orElseThrow(() -> new NotFoundException("Stock no encontrado para este producto en el local"));

        localProducto.setStock(request.getStock());
        localProductoRepository.save(localProducto);

        return Response.builder()
                .status(200)
                .message("Stock actualizado exitosamente")
                .build();
    }

    @Override
    public Response getStockPorLocal(Long localId) {
        List<LocalProducto> stocks = localProductoRepository.findByLocalId(localId);
        List<LocalProductoDTO> stockDTOs = modelMapper.map(stocks,
                new TypeToken<List<LocalProductoDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .localesProductos(stockDTOs)
                .build();
    }

    @Override
    public Response getStockProducto(Long localId, Long productoId) {
        LocalProducto localProducto = localProductoRepository
                .findByLocalIdAndProductoId(localId, productoId)
                .orElseThrow(() -> new NotFoundException("Stock no encontrado"));

        LocalProductoDTO dto = modelMapper.map(localProducto, LocalProductoDTO.class);

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .localProducto(dto)
                .build();
    }
}