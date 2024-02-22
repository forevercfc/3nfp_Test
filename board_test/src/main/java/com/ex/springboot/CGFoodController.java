package com.ex.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ex.springboot.dao.CGFoodDao;
import com.ex.springboot.dto.CgFood;

@Controller
public class CGFoodController {

    @Autowired
    private CGFoodDao cgFoodDao;
    
    @GetMapping("/cgfoods/new")
    public String showCreateForm(Model model) {
        model.addAttribute("cgFood", new CgFood());
        return "fooddb/createCgFood";
    }

    @PostMapping("/cgfoods")
    public String saveFood(@ModelAttribute CgFood cgFood) {
        cgFoodDao.save(cgFood);
        return "redirect:/cgfoods/list";
    }

    @GetMapping("/cgfoods/list")
    public String listFoods(Model model) {
        model.addAttribute("cgFoods", cgFoodDao.findAll());
        return "fooddb/listCgFoods";
    }
}
