package com.devamisoft.SistemaInventario.controllers;
import com.devamisoft.SistemaInventario.dtos.EmpresaDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.services.EmpresaServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {
    private final EmpresaServices empresaServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        return ResponseEntity.ok(empresaServices.createEmpresa(empresaDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllEmpresas() {
        return ResponseEntity.ok(empresaServices.getAllEmpresas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEmpresaById(@PathVariable Long id) {
        return ResponseEntity.ok(empresaServices.getEmpresaById(id));
    }

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<Response> getEmpresaByRuc(@PathVariable String ruc) {
        return ResponseEntity.ok(empresaServices.getEmpresaByRuc(ruc));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        return ResponseEntity.ok(empresaServices.updateEmpresa(id, empresaDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteEmpresa(@PathVariable Long id) {
        return ResponseEntity.ok(empresaServices.deleteEmpresa(id));
    }
}