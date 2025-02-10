package com.devamisoft.SistemaInventario.security;

import com.devamisoft.SistemaInventario.exceptions.NotFoundException;
import com.devamisoft.SistemaInventario.models.Usuarios;
import com.devamisoft.SistemaInventario.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            log.info("Buscando usuario: {}", username);

            Usuarios usuario = usuarioRepository.findByUsuarioWithRoles(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

            log.info("Usuario encontrado: {}", usuario.getUsuario());
            log.info("Rol del usuario: {}", usuario.getRol().getNombre());

            return AuthUser.builder()
                    .usuarios(usuario)
                    .build();

        } catch (Exception e) {
            log.error("Error al cargar el usuario: {}", e.getMessage());
            throw new UsernameNotFoundException("Error al cargar el usuario", e);
        }
    }
}