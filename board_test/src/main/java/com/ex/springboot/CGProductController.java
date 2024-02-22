package com.ex.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.ex.springboot.dao.CGProductDao;
import com.ex.springboot.dto.CgProduct;

@Controller
public class CGProductController {

    @Autowired
    private CGProductDao cgProductDao;
    
    @GetMapping("/cgproducts/new")
    public String showCreateForm(Model model) {
        model.addAttribute("cgProduct", new CgProduct());
        return "productdb/createCgProduct";
    }

    @PostMapping("/cgproducts")
    public String saveProduct(@ModelAttribute CgProduct cgProduct) {
        cgProductDao.save(cgProduct);
        return "redirect:/cgproducts/list";
    }

    @GetMapping("/cgproducts/list")
    public String listProducts(Model model) {
        model.addAttribute("cgProducts", cgProductDao.findAll());
        return "productdb/listCgProduct";
    }
}
