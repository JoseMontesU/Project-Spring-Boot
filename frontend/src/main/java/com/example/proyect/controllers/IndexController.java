package com.example.proyect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({ "/", "/inicio" })
    public String index() {
        return "index";
    }

}
