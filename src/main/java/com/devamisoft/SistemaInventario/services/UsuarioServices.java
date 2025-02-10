package com.devamisoft.SistemaInventario.services;

import com.devamisoft.SistemaInventario.dtos.*;
import com.devamisoft.SistemaInventario.models.Usuarios;

public interface UsuarioServices {
    Response registerUser(RegisterRequest registerRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    Usuarios getCurrentLoggedInUser();
    Response getUserById(Long id);
    Response updateUser(Long id, UsuariosDTO usuarioDTO);
    Response deleteUser(Long id);
}