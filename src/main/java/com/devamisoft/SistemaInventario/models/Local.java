package com.devamisoft.SistemaInventario.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locales")
@Data
@Builder
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locales")
    private Long idLocales;

    @NotBlank(message = "El código es requerido")
    @Column(name = "codigo", nullable = false, unique = true, length = 20)
    private String codigo;

    @NotBlank(message = "El nombre es requerido")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "estado", nullable = false)
    private Integer estado = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    // Relaciones inversas
    @OneToMany(mappedBy = "local")
    private List<LocalProducto> localesProducto;

    @OneToMany(mappedBy = "local")
    private List<Venta> ventas;

    @Override
    public String toString() {
        return "Local{" +
                "idLocales=" + idLocales +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", createdAt=" + createdAt +
                ", estado=" + estado +
                '}';
    }
}
