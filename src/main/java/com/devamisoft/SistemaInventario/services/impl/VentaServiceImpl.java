package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.VentaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.*;
import com.devamisoft.SistemaInventario.repositories.*;
import com.devamisoft.SistemaInventario.services.VentaServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaServices {

    private final VentaRepository ventaRepository;
    private final LocalRepository localRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Response createVenta(VentaDTO ventaDTO) {
        Local local = localRepository.findById(ventaDTO.getLocalId())
                .orElseThrow(() -> new NotFoundException("Local no encontrado"));

        Usuarios usuario = usuarioRepository.findById(ventaDTO.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        Venta venta = modelMapper.map(ventaDTO, Venta.class);
        venta.setLocal(local);
        venta.setUsuario(usuario);
        ventaRepository.save(venta);

        return Response.builder()
                .status(200)
                .message("Venta creada exitosamente")
                .venta(modelMapper.map(venta, VentaDTO.class))
                .build();
    }

    @Override
    public Response getAllVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventasDTO = modelMapper.map(ventas,
                new TypeToken<List<VentaDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .ventas(ventasDTO)
                .build();
    }

    @Override
    public Response getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .venta(modelMapper.map(venta, VentaDTO.class))
                .build();
    }

    @Override
    @Transactional
    public Response updateVenta(Long id, VentaDTO ventaDTO) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        if (ventaDTO.getLocalId() != null) {
            Local local = localRepository.findById(ventaDTO.getLocalId())
                    .orElseThrow(() -> new NotFoundException("Local no encontrado"));
            venta.setLocal(local);
        }

        if (ventaDTO.getUsuarioId() != null) {
            Usuarios usuario = usuarioRepository.findById(ventaDTO.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            venta.setUsuario(usuario);
        }

        modelMapper.map(ventaDTO, venta);
        venta.setIdVenta(id);
        ventaRepository.save(venta);

        return Response.builder()
                .status(200)
                .message("Venta actualizada exitosamente")
                .venta(modelMapper.map(venta, VentaDTO.class))
                .build();
    }

    @Override
    @Transactional
    public Response deleteVenta(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new NotFoundException("Venta no encontrada");
        }
        ventaRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Venta eliminada exitosamente")
                .build();
    }

    @Override
    public Response getVentasByLocal(Long localId) {
        List<Venta> ventas = ventaRepository.findByLocal_IdLocales(localId);
        // List<Venta> ventas = ventaRepository.findByLocalId(localId);

        List<VentaDTO> ventasDTO = modelMapper.map(ventas,
                new TypeToken<List<VentaDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .ventas(ventasDTO)
                .build();
    }

    @Override
    public Response getVentasByUsuario(Long usuarioId) {
        List<Venta> ventas = ventaRepository.findByUsuario_IdUsuarios(usuarioId);
        List<VentaDTO> ventasDTO = modelMapper.map(ventas,
                new TypeToken<List<VentaDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .ventas(ventasDTO)
                .build();
    }
}