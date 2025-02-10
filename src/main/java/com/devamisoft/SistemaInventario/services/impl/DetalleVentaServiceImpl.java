package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.DetalleVentaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.DetalleVenta;
import com.devamisoft.SistemaInventario.models.Producto;
import com.devamisoft.SistemaInventario.models.Venta;
import com.devamisoft.SistemaInventario.repositories.DetalleVentaRepository;
import com.devamisoft.SistemaInventario.repositories.ProductoRepository;
import com.devamisoft.SistemaInventario.repositories.VentaRepository;
import com.devamisoft.SistemaInventario.services.DetalleVentaServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceImpl implements DetalleVentaServices {

    private final DetalleVentaRepository detalleVentaRepository;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Response createDetalleVenta(DetalleVentaDTO detalleVentaDTO) {
        Venta venta = ventaRepository.findById(detalleVentaDTO.getVentaId())
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        Producto producto = productoRepository.findById(detalleVentaDTO.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        DetalleVenta detalle = modelMapper.map(detalleVentaDTO, DetalleVenta.class);
        detalle.setVenta(venta);
        detalle.setProducto(producto);

        // Calcular subtotal, impuesto y total
        detalle.setSubtotal(detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad())));
        BigDecimal porcentajeImpuesto = new BigDecimal(producto.getTipoImpuesto().getPorcentaje()).divide(new BigDecimal(100));
        detalle.setValorImpuesto(detalle.getSubtotal().multiply(porcentajeImpuesto));
        detalle.setTotal(detalle.getSubtotal().add(detalle.getValorImpuesto()));

        detalleVentaRepository.save(detalle);

        return Response.builder()
                .status(200)
                .message("Detalle de venta creado exitosamente")
                .detalleVenta(modelMapper.map(detalle, DetalleVentaDTO.class))
                .build();
    }

    @Override
    public Response getDetallesByVenta(Long ventaId) {
        List<DetalleVenta> detalles = detalleVentaRepository.findByVenta_IdVenta(ventaId);
        List<DetalleVentaDTO> detallesDTO = modelMapper.map(detalles,
                new TypeToken<List<DetalleVentaDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .detallesVenta(detallesDTO)
                .build();
    }

    @Override
    public Response getDetalleVentaById(Long id) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Detalle de venta no encontrado"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .detalleVenta(modelMapper.map(detalle, DetalleVentaDTO.class))
                .build();
    }

    @Override
    @Transactional
    public Response updateDetalleVenta(Long id, DetalleVentaDTO detalleVentaDTO) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Detalle de venta no encontrado"));

        if (detalleVentaDTO.getProductoId() != null) {
            Producto producto = productoRepository.findById(detalleVentaDTO.getProductoId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
            detalle.setProducto(producto);
        }

        modelMapper.map(detalleVentaDTO, detalle);
        detalle.setIdDetalleVenta(id);

        // Recalcular totales
        detalle.setSubtotal(detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad())));
        BigDecimal porcentajeImpuesto = new BigDecimal(detalle.getProducto().getTipoImpuesto().getPorcentaje()).divide(new BigDecimal(100));
        detalle.setValorImpuesto(detalle.getSubtotal().multiply(porcentajeImpuesto));
        detalle.setTotal(detalle.getSubtotal().add(detalle.getValorImpuesto()));

        detalleVentaRepository.save(detalle);

        return Response.builder()
                .status(200)
                .message("Detalle de venta actualizado exitosamente")
                .detalleVenta(modelMapper.map(detalle, DetalleVentaDTO.class))
                .build();
    }

    @Override
    @Transactional
    public Response deleteDetalleVenta(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new NotFoundException("Detalle de venta no encontrado");
        }
        detalleVentaRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Detalle de venta eliminado exitosamente")
                .build();
    }
}