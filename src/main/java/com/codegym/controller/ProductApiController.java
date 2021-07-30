package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class ProductApiController {
    @Autowired
    private ProductService postService;

    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> listPost() {
        Iterable<Product> posts = postService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}