package com.devamisoft.SistemaInventario.controllers;

import com.devamisoft.SistemaInventario.dtos.LoginRequest;
import com.devamisoft.SistemaInventario.dtos.RegisterRequest;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.services.UsuarioServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioServices usuarioServices;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(usuarioServices.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(usuarioServices.loginUser(loginRequest));
    }
}