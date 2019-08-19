package com.metal.reshetka.controller;


import com.metal.reshetka.model.Category;
import com.metal.reshetka.model.Post;
import com.metal.reshetka.repository.CategoryRepository;
import com.metal.reshetka.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    //region services
    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;
    //endregion

    @GetMapping("/list")
    public String list(Model model){

        List<Post> postList=postRepository.findAll();

        model.addAttribute("list",postList);

        return null;
    }

    @GetMapping("/{postId}/view")
    public String view(Model model, @PathVariable("postId") Long postId){

        Post post=postRepository.getOne(postId);

        model.addAttribute("post",post);

        return null;
    }
    @GetMapping("/{postId}/save")
    public String getPostForm(Model model,@PathVariable("postId") Long postId){

        if(postId==0){
            model.addAttribute("post",new Post());
        }
        else{
            Post post=postRepository.getOne(postId);

            model.addAttribute("post",post);
        }

        List<Category> categoryList=categoryRepository.findAll();

        model.addAttribute("categories",categoryList);

        return null;
    }

    @PostMapping("/save")
    public String save(Post post){

        postRepository.save(post);

        return "redirect:/post/"+post.getId()+"/view";
    }

    @PostMapping("/{postId}/save")
    public String delete(@PathVariable("postId") Long postId){

        Post post=postRepository.getOne(postId);

        postRepository.delete(post);

        return "redirect:/post/list";
    }

}
