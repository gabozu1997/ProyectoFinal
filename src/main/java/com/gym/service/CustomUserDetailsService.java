package com.gym.service;

import com.gym.dao.UsuarioDao;
import com.gym.dao.RolDao;
import com.gym.domain.Usuario;
import com.gym.domain.Rol;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsernameOrEmail(emailOrUsername, emailOrUsername);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + emailOrUsername);
        }

        List<Rol> roles = rolDao.findByIdUsuario(usuario.getIdUsuario());
        Collection<? extends GrantedAuthority> authorities = roles.stream()
                .map(r -> "ROLE_" + r.getNombre().trim().toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new CustomUserDetails(usuario, authorities);
    }
}
