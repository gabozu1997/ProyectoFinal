package com.gym.service;

import com.gym.domain.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> getUsuarios();

    public Usuario getUsuario(Usuario usuario);

    public Usuario getUsuarioPorUsername(String username);

    public Usuario getUsuarioPorUsernameYPassword(String username, String password);

    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo);

    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo);

    public void save(Usuario usuario, boolean crearRolUsuario);

    public void delete(Usuario usuario);

    boolean registrarUsuario(Usuario usuario);

    boolean confirmarUsuario(String codigo);

}
