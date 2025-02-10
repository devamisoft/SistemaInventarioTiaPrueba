package com.devamisoft.SistemaInventario.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tipo_impuesto")
@Data
@Builder
public class TipoImpuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_impuesto")
    private Long idImpuesto;

    @NotBlank(message = "El nombre es requerido")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotNull(message = "El porcentaje es requerido")
    @Column(name = "porcentaje", nullable = false)
    private Integer porcentaje;

    @Column(name = "created_at", updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "estado", nullable = false)
    @Builder.Default
    private Integer estado = 1;

    @OneToMany(mappedBy = "tipoImpuesto")
    private List<Producto> productos;
}