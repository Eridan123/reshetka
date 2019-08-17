package com.metal.reshetka.controller;

import com.metal.reshetka.model.UnitType;
import com.metal.reshetka.repository.UnitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/unitType")
public class UnitTypeController {

    //region services
    @Autowired
    UnitTypeRepository unitTypeRepository;
    //endregion

    @GetMapping("/list")
    public String list(Model model){

        List<UnitType> list=unitTypeRepository.findAll();

        model.addAttribute("list",list);

        return null;
    }

    @GetMapping("/{unitTypeId}/save")
    public String getSaveForm(Model model, @PathVariable("unitTypeId") Long unitTypeId){

        if(unitTypeId==0){
            model.addAttribute("unitType",new UnitType());
        }
        else{
            UnitType unitType=unitTypeRepository.getOne(unitTypeId);
            model.addAttribute("unitType",unitType);
        }

        return null;
    }

    @PostMapping("/save")
    public String save(UnitType unitType){

        unitTypeRepository.save(unitType);

        return "redirect:/unitType/list";
    }

    @PostMapping("/{unitTypeId}/delete")
    public String delete(@PathVariable("unitTypeId") Long unitTypeId){

        UnitType unitType=unitTypeRepository.getOne(unitTypeId);
        unitTypeRepository.delete(unitType);

        return "redirect:unitType/list";
    }
}
