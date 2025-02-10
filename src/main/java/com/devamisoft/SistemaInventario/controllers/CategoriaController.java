package com.devamisoft.SistemaInventario.controllers;
import com.devamisoft.SistemaInventario.dtos.CategoriaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.services.CategoriaServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaServices categoriaServices;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Response> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaServices.createCategoria(categoriaDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllCategorias() {
        return ResponseEntity.ok(categoriaServices.getAllCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaServices.getCategoriaById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Response> updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaServices.updateCategoria(id, categoriaDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaServices.deleteCategoria(id));
    }
}