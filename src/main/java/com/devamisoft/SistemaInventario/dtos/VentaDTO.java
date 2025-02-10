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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VentaDTO {
    private Long idVenta;
    private Long localId;
    private Long usuarioId;
    private LocalDateTime fechaVenta;

    @NotNull(message = "El subtotal es requerido")
    private BigDecimal subtotal;

    @NotNull(message = "El valor del impuesto es requerido")
    private BigDecimal valorImpuesto;

    @NotNull(message = "El total es requerido")
    private BigDecimal total;
    private Integer estado;
    private List<DetalleVentaDTO> detalles;
}