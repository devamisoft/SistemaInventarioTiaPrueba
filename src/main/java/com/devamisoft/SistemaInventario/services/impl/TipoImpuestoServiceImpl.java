package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.dtos.TipoImpuestoDTO;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.TipoImpuesto;
import com.devamisoft.SistemaInventario.repositories.TipoImpuestoRepository;
import com.devamisoft.SistemaInventario.services.TipoImpuestoServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoImpuestoServiceImpl implements TipoImpuestoServices {

    private final TipoImpuestoRepository tipoImpuestoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createTipoImpuesto(TipoImpuestoDTO tipoImpuestoDTO) {
        TipoImpuesto tipoImpuesto = modelMapper.map(tipoImpuestoDTO, TipoImpuesto.class);
        tipoImpuestoRepository.save(tipoImpuesto);

        return Response.builder()
                .status(200)
                .message("Tipo de impuesto creado exitosamente")
                .tipoImpuesto(modelMapper.map(tipoImpuesto, TipoImpuestoDTO.class))
                .build();
    }

    @Override
    public Response getAllTipoImpuestos() {
        List<TipoImpuesto> tipoImpuestos = tipoImpuestoRepository.findAll();
        List<TipoImpuestoDTO> tipoImpuestosDTO = modelMapper.map(tipoImpuestos,
                new TypeToken<List<TipoImpuestoDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .tiposImpuestos(tipoImpuestosDTO)
                .build();
    }

    @Override
    public Response getTipoImpuestoById(Long id) {
        TipoImpuesto tipoImpuesto = tipoImpuestoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de impuesto no encontrado"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .tipoImpuesto(modelMapper.map(tipoImpuesto, TipoImpuestoDTO.class))
                .build();
    }

    @Override
    public Response updateTipoImpuesto(Long id, TipoImpuestoDTO tipoImpuestoDTO) {
        TipoImpuesto tipoImpuesto = tipoImpuestoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de impuesto no encontrado"));

        modelMapper.map(tipoImpuestoDTO, tipoImpuesto);
        tipoImpuesto.setIdImpuesto(id);
        tipoImpuestoRepository.save(tipoImpuesto);

        return Response.builder()
                .status(200)
                .message("Tipo de impuesto actualizado exitosamente")
                .tipoImpuesto(modelMapper.map(tipoImpuesto, TipoImpuestoDTO.class))
                .build();
    }

    @Override
    public Response deleteTipoImpuesto(Long id) {
        if (!tipoImpuestoRepository.existsById(id)) {
            throw new NotFoundException("Tipo de impuesto no encontrado");
        }
        tipoImpuestoRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Tipo de impuesto eliminado exitosamente")
                .build();
    }
}