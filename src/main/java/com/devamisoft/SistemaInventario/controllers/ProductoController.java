package com.devamisoft.SistemaInventario.controllers;
import com.devamisoft.SistemaInventario.dtos.LocalProductoDTO;
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
    private final LocalProductoServices localProductoServices;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Response> createProducto(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoServices.createProducto(productoDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllProductos() {
        return ResponseEntity.ok(productoServices.getAllProductos());
    }

    // Asignación de stock a local
    @PostMapping("/asignar-stock")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Response> asignarStockALocal(@RequestBody LocalProductoDTO request) {
        return ResponseEntity.ok(localProductoServices.asignarStock(request));
    }

    // Actualizar stock
    @PutMapping("/actualizar-stock")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BODEGUERO')")
    public ResponseEntity<Response> actualizarStock(@RequestBody LocalProductoDTO request) {
        return ResponseEntity.ok(localProductoServices.actualizarStock(request));
    }

    // Consultar stock por local
    @GetMapping("/stock/local/{localId}")
    public ResponseEntity<Response> getStockPorLocal(@PathVariable Long localId) {
        return ResponseEntity.ok(localProductoServices.getStockPorLocal(localId));
    }

    // Consultar stock de un producto específico en un local
    @GetMapping("/stock/{localId}/{productoId}")
    public ResponseEntity<Response> getStockProducto(
            @PathVariable Long localId,
            @PathVariable Long productoId) {
        return ResponseEntity.ok(localProductoServices.getStockProducto(localId, productoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoServices.getProductoById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Response> updateProducto(
            @PathVariable Long id,
            @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoServices.updateProducto(id, productoDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Response> deleteProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoServices.deleteProducto(id));
    }
}
