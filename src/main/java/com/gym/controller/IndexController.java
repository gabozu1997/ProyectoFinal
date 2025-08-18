package com.gym.controller;

import com.gym.domain.Usuario;
import com.gym.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    private final UsuarioService usuarioService;

    public IndexController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/inscribete")
    public String inscribete() {
        return "inscribete";
    }

    @GetMapping("/nosotros")
    public String sobreNosotros() {
        return "nosotros";
    }

    @GetMapping("/faq")
    public String preguntasFrecuentes() {
        return "PreguntasFrecuentes";
    }

    @GetMapping("/contacto")
    public String contacto(@RequestParam(required = false) String enviado, Model model) {
        if (enviado != null) {
            model.addAttribute("mensaje", "¡Gracias! Pronto nos pondremos en contacto contigo.");
        }
        return "Contacto";
    }

    @PostMapping("/contacto/enviar")
    public String enviarContacto(@RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam(required = false) String telefono,
            @RequestParam String asunto,
            @RequestParam String preferencia,
            RedirectAttributes ra) {
        ra.addAttribute("enviado", "ok");
        return "redirect:/contacto";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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

    @GetMapping("/horarios")
    public String horarios() {
        return "Horarios";
    }
}
