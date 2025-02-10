package com.devamisoft.SistemaInventario.controllers;
import com.devamisoft.SistemaInventario.dtos.LocalDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.services.LocalServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locales")
@RequiredArgsConstructor
public class LocalController {
    private final LocalServices localServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createLocal(@RequestBody LocalDTO localDTO) {
        return ResponseEntity.ok(localServices.createLocal(localDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllLocales() {
        return ResponseEntity.ok(localServices.getAllLocales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getLocalById(@PathVariable Long id) {
        return ResponseEntity.ok(localServices.getLocalById(id));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<Response> getLocalesByEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(localServices.getLocalesByEmpresa(empresaId));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Response> getLocalByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(localServices.getLocalByCodigo(codigo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateLocal(@PathVariable Long id, @RequestBody LocalDTO localDTO) {
        return ResponseEntity.ok(localServices.updateLocal(id, localDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteLocal(@PathVariable Long id) {
        return ResponseEntity.ok(localServices.deleteLocal(id));
    }
}