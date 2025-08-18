package com.gym.controller;

import com.gym.domain.Usuario;
import com.gym.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private UsuarioService usuarioService;

    // Página principal
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Página Inscribirse
    @GetMapping("/inscribete")
    public String inscribete() {
        return "inscribete";
    }

    // Página perfil
    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }

    // Página nosotros
    @GetMapping("/nosotros")
    public String sobreNosotros() {
        return "SobreNosotros";
    }

    // Página de preguntas frecuentes
    @GetMapping("/faq")
    public String preguntasFrecuentes() {
        return "PreguntasFrecuentes";
    }

    // Página de contacto
    @GetMapping("/contacto")
    public String contacto() {
        return "Contacto";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(@RequestParam(required = false) String exito, Model model) {
        if (exito != null) {
            model.addAttribute("mensaje", "Registro exitoso. Revisa tu correo para confirmar tu cuenta.");
        }
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String password,
            @RequestParam String username,
            Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);
        usuario.setUsername(username);

        boolean registrado = usuarioService.registrarUsuario(usuario);
        if (!registrado) {
            model.addAttribute("error", "El correo o nombre de usuario ya están registrados.");
            return "registro";
        }
        return "redirect:/registro?exito";
    }

    @GetMapping("/confirmar")
    public String confirmarCuenta(@RequestParam String codigo, Model model) {
        boolean confirmado = usuarioService.confirmarUsuario(codigo);
        if (confirmado) {
            model.addAttribute("mensaje", "Cuenta confirmada con éxito. Ya puedes iniciar sesión.");
        } else {
            model.addAttribute("error", "Código inválido o cuenta ya confirmada.");
        }
        return "index";
    }
    // Página de horarios

    @GetMapping("/horarios")
    public String horarios() {
        return "Horarios";
    }

}
