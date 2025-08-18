package com.gym.repository;

import com.gym.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByEmail(String email);
    
    Usuario findByCodigoConfirmacion(String codigoConfirmacion);

    // Método corregido con 'email' en lugar de 'correo'
    boolean existsByUsernameOrEmail(String username, String email);
}