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

    // Mostrar formulario registro y mensaje de éxito opcional
    @GetMapping("/registro")
    public String mostrarRegistro(@RequestParam(required = false) String exito, Model model) {
        if (exito != null) {
            model.addAttribute("mensaje", "Registro exitoso. Revisa tu correo para confirmar tu cuenta.");
        }
        return "registro";
    }

    // Procesar formulario registro
    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String nombre,
                                   @RequestParam String email,
                                   @RequestParam String telefono,
                                   @RequestParam String password,
                                   Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);

        boolean registrado = usuarioService.registrarUsuario(usuario);
        if (!registrado) {
            model.addAttribute("error", "El correo ya está registrado.");
            return "registro";
        }
        // Redirige con parámetro para mostrar mensaje de éxito
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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }
}