package com.gym.service;

import com.gym.domain.Usuario;
import com.gym.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            return false; // usuario ya existe
        }
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public boolean confirmarUsuario(String codigo) {
        // Implementación futura
        return false;
    }
}