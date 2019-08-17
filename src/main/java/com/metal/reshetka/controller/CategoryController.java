package com.metal.reshetka.controller;

import com.metal.reshetka.model.Category;
import com.metal.reshetka.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    //region services
    @Autowired
    CategoryRepository categoryRepository;
    //endregion

    @GetMapping("/list")
    public String list(Model model){

        List<Category> list=categoryRepository.findAll();

        model.addAttribute("list",list);

        return null;
    }

    @GetMapping("/{categoryId}/save")
    public String getSaveForm(Model model, @PathVariable("categoryId") Long categoryId){

        if(categoryId==0){
            model.addAttribute("category",new Category());
        }
        else{
            Category category=categoryRepository.getOne(categoryId);
            model.addAttribute("category",category);
        }

        return null;
    }

    @PostMapping("/save")
    public String save(Category category){

        categoryRepository.save(category);

        return "redirect:/category/list";
    }

    @PostMapping("/{categoryId}/delete")
    public String delete(@PathVariable("categoryId") Long categoryId){

        Category category=categoryRepository.getOne(categoryId);
        categoryRepository.delete(category);

        return "redirect:category/list";
    }


}
