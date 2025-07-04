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
}
