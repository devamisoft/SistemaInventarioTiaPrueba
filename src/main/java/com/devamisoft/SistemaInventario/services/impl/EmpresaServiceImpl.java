package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.EmpresaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.Empresa;
import com.devamisoft.SistemaInventario.repositories.EmpresaRepository;
import com.devamisoft.SistemaInventario.services.EmpresaServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaServices {

    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);
        empresaRepository.save(empresa);

        return Response.builder()
                .status(200)
                .message("Empresa creada exitosamente")
                .empresa(modelMapper.map(empresa, EmpresaDTO.class))
                .build();
    }

    @Override
    public Response getAllEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        List<EmpresaDTO> empresasDTO = modelMapper.map(empresas,
                new TypeToken<List<EmpresaDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .empresas(empresasDTO)
                .build();
    }

    @Override
    public Response getEmpresaById(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .empresa(modelMapper.map(empresa, EmpresaDTO.class))
                .build();
    }

    @Override
    public Response updateEmpresa(Long id, EmpresaDTO empresaDTO) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));

        modelMapper.map(empresaDTO, empresa);
        empresa.setIdEmpresa(id);
        empresaRepository.save(empresa);

        return Response.builder()
                .status(200)
                .message("Empresa actualizada exitosamente")
                .empresa(modelMapper.map(empresa, EmpresaDTO.class))
                .build();
    }

    @Override
    public Response deleteEmpresa(Long id) {
        if (!empresaRepository.existsById(id)) {
            throw new NotFoundException("Empresa no encontrada");
        }
        empresaRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Empresa eliminada exitosamente")
                .build();
    }

    @Override
    public Response getEmpresaByRuc(String ruc) {
        Empresa empresa = empresaRepository.findByRuc(ruc)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .empresa(modelMapper.map(empresa, EmpresaDTO.class))
                .build();
    }
}