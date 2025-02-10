package com.devamisoft.SistemaInventario.controllers;
import com.devamisoft.SistemaInventario.dtos.ProductoDTO;
import com.devamisoft.SistemaInventario.dtos.Response;
import com.devamisoft.SistemaInventario.services.ProductoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoServices productoServices;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Response> createProducto(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoServices.createProducto(productoDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllProductos() {
        return ResponseEntity.ok(productoServices.getAllProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoServices.getProductoById(id));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Response> getProductosByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoServices.getProductosByCategoria(categoriaId));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Response> getProductoByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(productoServices.getProductosByCodigo(codigo));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<Response> getProductosByEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(productoServices.getProductosByEmpresa(empresaId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Response> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoServices.updateProducto(id, productoDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoServices.deleteProducto(id));
    }
}
