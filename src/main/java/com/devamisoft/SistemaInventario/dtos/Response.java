package com.devamisoft.SistemaInventario.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int status;
    private String message;
    private String token;
    private String rolNombre;
    private String expirationTime;
    private Integer totalPages;
    private Long totalElements;

    private UsuariosDTO usuario;
    private List<UsuariosDTO> usuarios;
    private EmpresaDTO empresa;
    private List<EmpresaDTO> empresas;
    private RolDTO rol;
    private List<RolDTO> roles;
    private CategoriaDTO categoria;
    private List<CategoriaDTO> categorias;
    private ProductoDTO producto;
    private List<ProductoDTO> productos;
    private LocalDTO local;
    private List<LocalDTO> locales;
    private LocalProductoDTO localProducto;
    private List<LocalProductoDTO> localesProductos;
    private TipoImpuestoDTO tipoImpuesto;
    private List<TipoImpuestoDTO> tiposImpuestos;
    private VentaDTO venta;
    private List<VentaDTO> ventas;
    private DetalleVentaDTO detalleVenta;
    private List<DetalleVentaDTO> detallesVenta;
    private InventarioMovimientoDTO inventarioMovimiento;
    private List<InventarioMovimientoDTO> inventarioMovimientos;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }


}