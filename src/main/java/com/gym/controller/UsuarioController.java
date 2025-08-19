package com.gym.controller;

import com.gym.Repository.UsuarioRepository;
import com.gym.domain.Usuario;
import com.gym.domain.UsuarioMembresia;
import com.gym.service.UsuarioMembresiaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMembresiaService usuarioMembresiaService;

    public UsuarioController(UsuarioRepository usuarioRepository,
            UsuarioMembresiaService usuarioMembresiaService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMembresiaService = usuarioMembresiaService;
    }

   
    @GetMapping("/perfil")
    public String verPerfil(Model model, @AuthenticationPrincipal UserDetails auth) {
        if (auth == null) {
            return "redirect:/login";
        }

        String principal = auth.getUsername();
        Usuario u = usuarioRepository.findByEmailIgnoreCase(principal);
        if (u == null) {
            u = usuarioRepository.findByUsername(principal);
        }

        String membresiaNombre = null;
        if (u != null) {
            Optional<UsuarioMembresia> om = usuarioMembresiaService.obtenerActiva(u.getIdUsuario());
            if (om.isPresent() && om.get().getPlan() != null) {
                membresiaNombre = om.get().getPlan().getNombre();
            }
        }

        model.addAttribute("usuarioActual", u);      
        model.addAttribute("membresiaNombre", membresiaNombre);
        return "perfil";
    }

  
    @GetMapping("/perfil/editar")
    public String editarPerfil(Model model, @AuthenticationPrincipal UserDetails auth) {
        if (auth == null) {
            return "redirect:/login";
        }
        String principal = auth.getUsername();
        Usuario u = usuarioRepository.findByEmailIgnoreCase(principal);
        if (u == null) {
            u = usuarioRepository.findByUsername(principal);
        }
        model.addAttribute("u", u);
        return "perfil-editar";
    }

   
    @PostMapping("/perfil/editar")
    public String guardarPerfil(@ModelAttribute("u") Usuario form,
            @AuthenticationPrincipal UserDetails auth,
            RedirectAttributes ra) {
        if (auth == null) {
            return "redirect:/login";
        }
        String principal = auth.getUsername();
        Usuario u = usuarioRepository.findByEmailIgnoreCase(principal);
        if (u == null) {
            u = usuarioRepository.findByUsername(principal);
        }
        if (u == null) {
            ra.addFlashAttribute("error", "No se encontró el usuario autenticado.");
            return "redirect:/perfil";
        }

        u.setNombre(form.getNombre());
        u.setTelefono(form.getTelefono());
        u.setEmail(form.getEmail());
        usuarioRepository.save(u);

        ra.addFlashAttribute("mensaje", "Perfil actualizado.");
        return "redirect:/perfil";
    }
}
