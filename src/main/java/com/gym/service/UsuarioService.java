package com.gym.service;

import com.gym.domain.Usuario;

public interface UsuarioService {
    boolean registrarUsuario(Usuario usuario);
    boolean confirmarUsuario(String codigo);
}