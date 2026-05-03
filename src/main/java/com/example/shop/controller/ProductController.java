package com.example.shop.controller;

import com.example.shop.database.entity.Product;
import com.example.shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,@Valid @RequestBody Product product){
        return productService.update(id, product);
    }
}
