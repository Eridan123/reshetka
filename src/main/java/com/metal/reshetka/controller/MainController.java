package com.metal.reshetka.controller;

import com.metal.reshetka.model.Category;
import com.metal.reshetka.model.User;
import com.metal.reshetka.repository.CategoryRepository;
import com.metal.reshetka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    //region services
    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;
    //endregion

    @GetMapping("/users")
    public String getUserList(Model model){

        List<User> users=userRepository.findAll();
        model.addAttribute("users",users);

        return "/users";
    }

    @GetMapping("/")
    public String getIndex(Model model){

        List<Category> categoryList=categoryRepository.findAll();

        model.addAttribute("categories",categoryList);


        return "/index";
    }
}
