package com.devamisoft.SistemaInventario.services.impl;

import com.devamisoft.SistemaInventario.dtos.*;
import com.devamisoft.SistemaInventario.exceptions.InvalidCredentialsException;
import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.Empresa;
import com.devamisoft.SistemaInventario.models.Rol;
import com.devamisoft.SistemaInventario.models.Usuarios;
import com.devamisoft.SistemaInventario.repositories.EmpresaRepository;
import com.devamisoft.SistemaInventario.repositories.RolRepository;
import com.devamisoft.SistemaInventario.repositories.UsuarioRepository;
import com.devamisoft.SistemaInventario.security.JwtUtils;
import com.devamisoft.SistemaInventario.services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;
    private final EmpresaRepository empresaRepository;
    private final RolRepository rolRepository;

    @Override
    public Response registerUser(RegisterRequest registerRequest) {
        Empresa empresa = empresaRepository.findById(registerRequest.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));

        Rol rol = rolRepository.findById(registerRequest.getRolId())
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));

        Usuarios usuarioToSave = Usuarios.builder()
                .usuario(registerRequest.getUsuario())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .correo(registerRequest.getCorreo())
                .nombreCompleto(registerRequest.getNombreCompleto())
                .empresa(empresa)
                .rol(rol)
                .estado(1)
                .build();

        usuarioRepository.save(usuarioToSave);

        return Response.builder()
                .status(200)
                .message("Usuario registrado exitosamente")
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        Usuarios usuario = usuarioRepository.findByUsuario(loginRequest.getUsuario())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException("Contraseña incorrecta");
        }

        String token = jwtUtils.generateToken(usuario.getUsuario());

        return Response.builder()
                .status(200)
                .message("Inicio de sesión exitoso")
                .rolNombre(usuario.getRol().getNombre())
                .token(token)
                .expirationTime("6 meses")
                .build();
    }

    @Override
    public Response getAllUsers() {
        List<Usuarios> usuarios = usuarioRepository.findAll(Sort.by(Sort.Direction.DESC, "idUsuarios"));
        List<UsuariosDTO> usuariosDTO = modelMapper.map(usuarios, new TypeToken<List<UsuariosDTO>>() {}.getType());

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .usuarios(usuariosDTO)
                .build();
    }

    @Override
    public Usuarios getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    @Override
    public Response getUserById(Long id) {
        Usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        UsuariosDTO usuarioDTO = modelMapper.map(usuario, UsuariosDTO.class);

        return Response.builder()
                .status(200)
                .message("Consulta exitosa")
                .usuario(usuarioDTO)
                .build();
    }

    @Override
    public Response updateUser(Long id, UsuariosDTO usuarioDTO) {
        Usuarios existingUser = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (usuarioDTO.getUsuario() != null) existingUser.setUsuario(usuarioDTO.getUsuario());
        if (usuarioDTO.getCorreo() != null) existingUser.setCorreo(usuarioDTO.getCorreo());
        if (usuarioDTO.getNombreCompleto() != null) existingUser.setNombreCompleto(usuarioDTO.getNombreCompleto());

        if (usuarioDTO.getRolId() != null) {
            Rol rol = rolRepository.findById(usuarioDTO.getRolId())
                    .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
            existingUser.setRol(rol);
        }

        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }

        usuarioRepository.save(existingUser);

        return Response.builder()
                .status(200)
                .message("Usuario actualizado exitosamente")
                .build();
    }

    @Override
    public Response deleteUser(Long id) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        usuarioRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Usuario eliminado exitosamente")
                .build();
    }
}