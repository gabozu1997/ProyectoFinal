package com.gym.controller;

import com.gym.Repository.UsuarioRepository;
import com.gym.domain.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Ver perfil
    @GetMapping("/perfil")
    public String verPerfil(Model model, @AuthenticationPrincipal UserDetails auth) {
        Usuario u = usuarioRepository.findByEmailIgnoreCase(auth.getUsername());
        model.addAttribute("u", u);
        return "perfil"; // templates/perfil.html
    }

    // Formulario de edición de perfil
    @GetMapping("/perfil/editar")
    public String editarPerfil(Model model, @AuthenticationPrincipal UserDetails auth) {
        Usuario u = usuarioRepository.findByEmailIgnoreCase(auth.getUsername());
        model.addAttribute("u", u);
        return "perfil-editar"; // templates/perfil-editar.html
    }

    // Guardar cambios de perfil
    @PostMapping("/perfil/editar")
    public String guardarPerfil(
            @ModelAttribute("u") Usuario form,
            @AuthenticationPrincipal UserDetails auth,
            RedirectAttributes ra) {

        Usuario u = usuarioRepository.findByEmailIgnoreCase(auth.getUsername());
        if (u == null) {
            ra.addFlashAttribute("err", "No se encontró el usuario autenticado.");
            return "redirect:/perfil";
        }

        u.setNombre(form.getNombre());
        u.setTelefono(form.getTelefono());
        // Si NO quieres permitir cambiar el correo, comenta la siguiente línea
        u.setEmail(form.getEmail());

        usuarioRepository.save(u);
        ra.addFlashAttribute("ok", "Perfil actualizado.");
        return "redirect:/perfil";
    }
}
