package org.example.examenii_i_ciclo_2023.presentation.controllers.controllers;

import org.example.examenii_i_ciclo_2023.data.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final CategoriaRepository userRepository;



//--------------------------------------------------
    @Autowired
    public WebController(CategoriaRepository userRepository) {
        this.userRepository = userRepository;
    }
//--------------------------------------------------

//--------------------------------------------------
    @GetMapping("/meetings")
    public String meetings(){
        return "/meetings";
    }
//--------------------------------------------------
    @GetMapping("/")
    public String index(){
        return "/meetings";
    }
//--------------------------------------------------
}
