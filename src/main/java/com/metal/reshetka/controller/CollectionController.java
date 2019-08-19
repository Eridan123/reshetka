package com.metal.reshetka.controller;


import com.metal.reshetka.model.Collection;
import com.metal.reshetka.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    //region services
    @Autowired
    CollectionRepository collectionRepository;
    //endregion

    @GetMapping("/list")
    public String list(Model model){

        List<Collection> collectionList=collectionRepository.findAll();

        model.addAttribute("collections",collectionList);

        return null;
    }

    @GetMapping("/{collectionId}/view")
    public String view(Model model, @PathVariable("collectionId") Long collectionId){

        Collection collection=collectionRepository.getOne(collectionId);

        model.addAttribute("collection",collection);

        return null;
    }

    @GetMapping("/{collectionId}/save")
    public String getCollectionForm(Model model,@PathVariable("collectionId") Long collectionId){

        if(collectionId==0){
            model.addAttribute("collection",new Collection());
        }
        else{
            Collection collection=collectionRepository.getOne(collectionId);

            model.addAttribute("collection",collection);
        }

        return null;
    }

    @PostMapping("/save")
    public String save(Collection collection){

        collectionRepository.save(collection);

        return "redirect:/collection/"+collection.getId()+"/view";
    }

    @PostMapping("/{collectionId}/delete")
    public String delete(@PathVariable("collectionId") Long collectionId){

        Collection collection=collectionRepository.getOne(collectionId);
        collectionRepository.delete(collection);

        return "redirect:/colleciton/list";
    }
}
