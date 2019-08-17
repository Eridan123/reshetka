package com.metal.reshetka.controller;

import com.metal.reshetka.model.Role;
import com.metal.reshetka.model.User;
import com.metal.reshetka.model.UserRole;
import com.metal.reshetka.repository.RoleRepository;
import com.metal.reshetka.repository.UserRepository;
import com.metal.reshetka.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    //region services
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;
    //end region

    @GetMapping("/list")
    public String list(Model model){

        List<User> list=userRepository.findAll();

        model.addAttribute("list",list);

        return null;
    }

    @GetMapping("/{userId}/view")
    public String view(Model model,@PathVariable("userId") Long userId){

        User user=userRepository.getOne(userId);

        model.addAttribute("user",user);

        return null;
    }

    @GetMapping("/{userId}/save")
    public String getUserForm(Model model, @PathVariable("userId") Long userId){

        if(userId==0){
            model.addAttribute("user",new User());
        }
        else if(userId>0){
            User user=userRepository.getOne(userId);
            model.addAttribute("user",user);
        }


        return null;
    }

    @PostMapping("/save")
    public String save(User user){

        userRepository.save(user);

        Role role=new Role();
        try{
            role=roleRepository.getOne(1L);
        }
        catch (Exception e){
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

        UserRole userRole=new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoleRepository.save(userRole);

        return "redirect:/user/"+user.getId()+"/view";
    }

    @PostMapping("/{userId}/delete")
    public String delete(@PathVariable("userId") Long userId){

        User user=userRepository.getOne(userId);
        userRepository.delete(user);

        return "redirect/user/list";
    }

}
