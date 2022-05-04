package com.example.proyect.controllers;

import com.example.proyect.models.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    private final RestTemplate restTemplate;

    @Autowired
    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        String url = "http://localhost:8080/api/users";
        UserModel[] userList = restTemplate.getForObject(url, UserModel[].class);
        // System.out.println(productList[0]);
        model.addAttribute("userList", userList);
        return "userList";
    }

    @GetMapping("/form")
    public String addUser(Model model) {
        model.addAttribute("user", new UserModel());
        model.addAttribute("user1", new UserModel());
        return "form";
    }

    @RequestMapping("/save")
    public String save(@Validated UserModel user, Model model) {
        user.setActive(true);
        String url = "http://localhost:8080/api/users";
        String respuesta = restTemplate.postForObject(url, user, String.class);
        System.out.println(respuesta);
        url = "http://localhost:8080/api/users/session?username=" + user.getUser() + "&password=" + user.getPassword();
        Boolean valor = restTemplate.getForObject(url, Boolean.class);
        System.out.println(valor);
        model.addAttribute("username", user.getUser());
        return "index";
    }

    @RequestMapping("/signin")
    public String singin(@Validated UserModel user, Model model) {
        System.out.println(user.getUser());
        String url = "http://localhost:8080/api/users/session?username=" + user.getUser() + "&password="
                + user.getPassword();
        Boolean valor = restTemplate.getForObject(url, Boolean.class);
        System.out.println(valor);
        if (valor) {
            model.addAttribute("username", user.getUser());
        } else {
            model.addAttribute("username", "Incognito");
        }

        return "index";
    }
}
