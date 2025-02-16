package com.devamisoft.SistemaInventario.controllers;

import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.dtos.UsuariosDTO;
import com.devamisoft.SistemaInventario.models.Usuarios;
import com.devamisoft.SistemaInventario.services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioServices usuarioServices;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> getAllUsers() {
        return ResponseEntity.ok(usuarioServices.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioServices.getUserById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id, @RequestBody UsuariosDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioServices.updateUser(id, usuarioDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioServices.deleteUser(id));
    }

    @GetMapping("/current")
    public ResponseEntity<Usuarios> getCurrentUser() {
        return ResponseEntity.ok(usuarioServices.getCurrentLoggedInUser());
    }
}