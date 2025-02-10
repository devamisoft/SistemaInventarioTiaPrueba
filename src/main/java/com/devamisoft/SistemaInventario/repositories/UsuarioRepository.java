package com.devamisoft.SistemaInventario.repositories;

import com.devamisoft.SistemaInventario.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    @Procedure(name = "sp_get_usuario_with_rol")
    Optional<Usuarios> findByUsuarioWithRol(@Param("p_usuario") String usuario);

    @Query("SELECT u FROM Usuarios u JOIN FETCH u.rol r JOIN FETCH u.empresa e WHERE u.usuario = :usuario AND u.estado = 1")
    Optional<Usuarios> findByUsuarioWithRoles(@Param("usuario") String usuario);

    Optional<Usuarios> findByUsuario(String usuario);
    Optional<Usuarios> findByCorreo(String correo);
    List<Usuarios> findByEmpresa_IdEmpresaAndEstado(Long empresaId, Integer estado);
    boolean existsByUsuario(String usuario);
    boolean existsByCorreo(String correo);
}