package com.piedade.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piedade.spring.domain.product.Product;
import com.piedade.spring.domain.product.ProductRepository;
import com.piedade.spring.domain.product.RequestProduct;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = repository.findAll();
        return ResponseEntity.ok().body(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data ){
        Product product = new Product(data);
        repository.save(product);

        
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data ){
        System.out.println(data);
        Product product = repository.findById(data.id()).orElseThrow();
        product.setName(data.name());
        product.setPrice_in_cents(data.price_in_cents());

        return ResponseEntity.ok().body(product);
    }
}
