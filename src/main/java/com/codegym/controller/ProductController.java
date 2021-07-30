package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.CategoryService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "/product/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product, Model model) {
        productService.save(product);
        model.addAttribute("product", new Product());
        return "redirect:list";
    }

    @GetMapping("/list")
    public ModelAndView list(@PageableDefault(size = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editform(@PathVariable Long id) {
        Optional<Product> product = productService.fidById(id);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product.get());
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("product") Product product, Model model) {

        productService.save(product);
        return "redirect:list";
    }

    @GetMapping("/api")
    public String list() {
        return "/list";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") String search) {
        Iterable<Product> products = productService.findByName("%" + search + "%");
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
