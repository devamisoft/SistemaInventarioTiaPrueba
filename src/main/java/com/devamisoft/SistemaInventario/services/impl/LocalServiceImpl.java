package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.LocalDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.Empresa;
import com.devamisoft.SistemaInventario.models.Local;
import com.devamisoft.SistemaInventario.repositories.EmpresaRepository;
import com.devamisoft.SistemaInventario.repositories.LocalRepository;
import com.devamisoft.SistemaInventario.services.LocalServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalServiceImpl implements LocalServices {

    private final LocalRepository localRepository;
    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createLocal(LocalDTO localDTO) {
        Empresa empresa = empresaRepository.findById(localDTO.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));

        Local local = modelMapper.map(localDTO, Local.class);
        local.setEmpresa(empresa);
        localRepository.save(local);

        return Response.builder()
                .status(200)
                .message("Local creado exitosamente")
                .local(modelMapper.map(local, LocalDTO.class))
                .build();
    }

    @Override
    public Response getAllLocales() {
        List<Local> locales = localRepository.findAll();
        List<LocalDTO> localesDTO = modelMapper.map(locales,
                new TypeToken<List<LocalDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .locales(localesDTO)
                .build();
    }

    @Override
    public Response getLocalById(Long id) {
        Local local = localRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Local no encontrado"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .local(modelMapper.map(local, LocalDTO.class))
                .build();
    }

    @Override
    public Response updateLocal(Long id, LocalDTO localDTO) {
        Local local = localRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Local no encontrado"));

        if (localDTO.getEmpresaId() != null) {
            Empresa empresa = empresaRepository.findById(localDTO.getEmpresaId())
                    .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));
            local.setEmpresa(empresa);
        }

        modelMapper.map(localDTO, local);
        local.setIdLocales(id);
        localRepository.save(local);

        return Response.builder()
                .status(200)
                .message("Local actualizado exitosamente")
                .local(modelMapper.map(local, LocalDTO.class))
                .build();
    }

    @Override
    public Response deleteLocal(Long id) {
        if (!localRepository.existsById(id)) {
            throw new NotFoundException("Local no encontrado");
        }
        localRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Local eliminado exitosamente")
                .build();
    }

    @Override
    public Response getLocalesByEmpresa(Long empresaId) {
        List<Local> locales = localRepository.findByEmpresa_IdEmpresaAndEstado(empresaId, 1);
        List<LocalDTO> localesDTO = modelMapper.map(locales,
                new TypeToken<List<LocalDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .locales(localesDTO)
                .build();
    }

    @Override
    public Response getLocalByCodigo(String codigo) {
        Local local = localRepository.findByCodigo(codigo)
                .orElseThrow(() -> new NotFoundException("Local no encontrado"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .local(modelMapper.map(local, LocalDTO.class))
                .build();
    }
}