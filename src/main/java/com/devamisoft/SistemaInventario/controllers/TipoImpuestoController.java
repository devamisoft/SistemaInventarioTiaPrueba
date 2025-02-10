package com.devamisoft.SistemaInventario.controllers;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.dtos.TipoImpuestoDTO;
import com.devamisoft.SistemaInventario.services.TipoImpuestoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipo-impuesto")
@RequiredArgsConstructor
public class TipoImpuestoController {
    private final TipoImpuestoServices tipoImpuestoServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createTipoImpuesto(@RequestBody TipoImpuestoDTO tipoImpuestoDTO) {
        return ResponseEntity.ok(tipoImpuestoServices.createTipoImpuesto(tipoImpuestoDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllTipoImpuestos() {
        return ResponseEntity.ok(tipoImpuestoServices.getAllTipoImpuestos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getTipoImpuestoById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoImpuestoServices.getTipoImpuestoById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateTipoImpuesto(@PathVariable Long id, @RequestBody TipoImpuestoDTO tipoImpuestoDTO) {
        return ResponseEntity.ok(tipoImpuestoServices.updateTipoImpuesto(id, tipoImpuestoDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteTipoImpuesto(@PathVariable Long id) {
        return ResponseEntity.ok(tipoImpuestoServices.deleteTipoImpuesto(id));
    }
}