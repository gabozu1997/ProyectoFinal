package com.gym.repository;

import com.gym.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
    Usuario findByCodigoConfirmacion(String codigoConfirmacion);
}