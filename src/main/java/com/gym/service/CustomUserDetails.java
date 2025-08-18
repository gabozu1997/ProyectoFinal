package com.gym.service;

import com.gym.domain.Usuario;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    private final String nombre;
    private final String apellidos;
    private final String rutaImagen; // viene de BD tal cual

    public CustomUserDetails(Usuario u, Collection<? extends GrantedAuthority> authorities) {
        this.username = (u.getEmail() != null ? u.getEmail() : u.getUsername());
        this.password = u.getPassword();
        this.enabled = u.isActivo();
        this.authorities = authorities;

        this.nombre = u.getNombre();
        this.apellidos = u.getApellidos();
        this.rutaImagen = u.getRutaImagen(); // SIN tocar
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }
}
