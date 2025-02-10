package com.devamisoft.SistemaInventario.dtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetalleVentaDTO {
    private Long idDetalleVenta;
    private Long ventaId;
    private Long productoId;

    @NotNull(message = "La cantidad es requerida")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es requerido")
    private BigDecimal precioUnitario;

    @NotNull(message = "El subtotal es requerido")
    private BigDecimal subtotal;

    @NotNull(message = "El valor del impuesto es requerido")
    private BigDecimal valorImpuesto;

    @NotNull(message = "El total es requerido")
    private BigDecimal total;
    private LocalDateTime createdAt;
}