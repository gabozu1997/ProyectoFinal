
package com.gym.controller;

import com.gym.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UsuarioController {

    @ModelAttribute("usuarioActual")
    public CustomUserDetails usuarioActual(Authentication authentication) {
        if (authentication == null) return null;
        Object p = authentication.getPrincipal();
        return (p instanceof CustomUserDetails) ? (CustomUserDetails)p : null;
    }
}