package com.gym.Repository;

import com.gym.domain.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembresiaRepository extends JpaRepository<Membresia, Long> {

    // Esto es lo que te fallaba: ahora sí existen 'activo' y 'precioMensual' en la entidad
    List<Membresia> findByActivoTrueOrderByPrecioMensualAsc();
}
