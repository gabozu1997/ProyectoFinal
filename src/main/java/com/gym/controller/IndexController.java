package com.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @GetMapping("/") //URL al que responde
    public String index() {
        return "index"; // Nombre de la vista en templates a mostrar
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }
    
    @GetMapping("/registro")// Mostrar formulario de registro
    public String mostrarRegistro() {
        return "registro"; // muestra registro.html
    }

    // Procesar formulario de registro
    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String nombre,
                                  @RequestParam String email,
                                  @RequestParam String password) {
        // Aquí se agregaría lógica para guardar usuario en la BD
        System.out.println("Usuario registrado: " + nombre + ", " + email);
        return "redirect:/"; // redirige a la página principal tras registro
    }
    
}
