package com.gym.dao;

import com.gym.domain.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<Rol, Long> {
    List<Rol> findByIdUsuario(Long idUsuario);
}
