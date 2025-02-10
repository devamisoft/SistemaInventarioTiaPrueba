package com.devamisoft.SistemaInventario.security;

import com.devamisoft.SistemaInventario.models.Usuarios;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class AuthUser implements UserDetails {
    private Usuarios usuarios;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority("ROLE_" + usuarios.getRol().getNombre()));
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuarios.getRol().getNombre()));

    }

    @Override
    public String getPassword() {
        return usuarios.getPassword();
    }

    @Override
    public String getUsername() {
        return usuarios.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuarios.getEstado() == 1;
    }

    public Long getEmpresaId() {
        return usuarios.getEmpresa().getIdEmpresa();
    }

    public String getNombreCompleto() {
        return usuarios.getNombreCompleto();
    }

    public String getCorreo() {
        return usuarios.getCorreo();
    }

    public Long getRolId() {
        return usuarios.getRol().getIdRol();
    }

    public String getNombreRol() {
        return usuarios.getRol().getNombre();
    }
}