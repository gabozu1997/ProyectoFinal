package com.gym.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotEmpty(message = "El username no puede estar vacío")
    private String username;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    private String apellidos;

    @Email(message = "Debe ser un email válido")
    @NotEmpty(message = "El email no puede estar vacío")
    private String email;

    private String telefono;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;

    @Column(nullable = true)
    private String rutaImagen;

    private boolean activo;

    @Column(nullable = true)
    private String codigoConfirmacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_usuario")
    private List<Rol> roles;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String username, String password, String nombre, String apellidos,
            String email, String telefono, String rutaImagen, boolean activo,
            String codigoConfirmacion, List<Rol> roles) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
        this.codigoConfirmacion = codigoConfirmacion;
        this.roles = roles;
    }
}
