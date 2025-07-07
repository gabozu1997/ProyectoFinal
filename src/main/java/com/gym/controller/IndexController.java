package com.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/") // Inicio
    public String index() {
        return "index";
    }

    @GetMapping("/inscribete")
    public String inscribete() {
        return "inscribete";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }

    @GetMapping("/nosotros")
    public String sobreNosotros() {
        return "SobreNosotros";
    }
    @GetMapping("/faq")
    public String preguntasFrecuentes() {
        return "PreguntasFrecuentes";
    }
    
    @GetMapping("/contacto")
public String contacto() {
    return "Contacto";
}
   
}

