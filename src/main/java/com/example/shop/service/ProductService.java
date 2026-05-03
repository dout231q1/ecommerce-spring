package com.example.shop.service;

import com.example.shop.database.entity.Product;
import com.example.shop.database.repository.ProductRepository;
import com.example.shop.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void delete(Long id){
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product product){
        Product existing = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.updateFrom(product);
        return productRepository.save(existing);
    }
}
