package com.devamisoft.SistemaInventario.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresa")
@Data
@Builder
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @NotBlank(message = "RUC es requerido")
    @Column(name = "ruc", unique = true, nullable = false, length = 13)
    private String ruc;

    @NotBlank(message = "Nombre es requerido")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "nombre_comercial", length = 100)
    private String nombreComercial;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "created_at", updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "estado", nullable = false)
    @Builder.Default
    private Integer estado = 1;

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", ruc='" + ruc + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nombreComercial='" + nombreComercial + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", createdAt=" + createdAt +
                ", estado=" + estado +
                '}';
    }
}