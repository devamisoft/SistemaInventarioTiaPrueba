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
@Table(name = "usuarios")
@NamedStoredProcedureQuery(
        name = "sp_get_usuario_with_rol",
        procedureName = "sp_get_usuario_with_rol",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_usuario", type = String.class)
        }
)
@Data
@Builder

public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Long idUsuarios;

    @NotBlank(message = "Usuario es requerido")
    @Column(name = "usuario", unique = true, nullable = false, length = 50)
    private String usuario;

    @NotBlank(message = "Password es requerido")
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "nombre_completo", length = 100)
    private String nombreCompleto;

    @Column(name = "created_at", updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "estado", nullable = false)
    @Builder.Default
    private Integer estado = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", referencedColumnName = "id_empresa")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", referencedColumnName = "id_rol", nullable = false)
    private Rol rol;

    @Override
    public String toString() {
        return "Usuarios{" +
                "idUsuarios=" + idUsuarios +
                ", usuario='" + usuario + '\'' +
                ", correo='" + correo + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", createdAt=" + createdAt +
                ", estado=" + estado +
                '}';
    }

}
