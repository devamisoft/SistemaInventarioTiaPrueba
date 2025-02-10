package com.devamisoft.SistemaInventario.controllers;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.dtos.VentaDTO;
import com.devamisoft.SistemaInventario.services.VentaServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {
    private final VentaServices ventaServices;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Response> createVenta(@RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok(ventaServices.createVenta(ventaDTO));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Response> getAllVentas() {
        return ResponseEntity.ok(ventaServices.getAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getVentaById(@PathVariable Long id) {
        return ResponseEntity.ok(ventaServices.getVentaById(id));
    }

    @GetMapping("/local/{localId}")
    public ResponseEntity<Response> getVentasByLocal(@PathVariable Long localId) {
        return ResponseEntity.ok(ventaServices.getVentasByLocal(localId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Response> getVentasByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ventaServices.getVentasByUsuario(usuarioId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Response> updateVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok(ventaServices.updateVenta(id, ventaDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaServices.deleteVenta(id));
    }
}