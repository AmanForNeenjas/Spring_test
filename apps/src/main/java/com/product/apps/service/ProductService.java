package com.product.apps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.product.apps.model.Product;
import com.product.apps.repository.ProductRepository;
@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
        public Product saveProduct(Product product) {
                // Business logic to save product
                return productRepository.save(product);
        }   
       
    @GetMapping
    public List<Product> getEveryProduct() {
        return productRepository.findAll();
    }
        public Product getProductByName(String name) {
                // Business logic to get product by name
                return productRepository.findByName(name);
        }   
        public void deleteProduct(Long id) {
                // Business logic to delete product
                productRepository.deleteById(id);
        }
        public Product updateProduct(Long id, Product updatedProduct) {
                // Business logic to update product
                return productRepository.findById(id).map(product -> {
                        product.setName(updatedProduct.getName());
                        product.setPrice(updatedProduct.getPrice());
                        product.setDescription(updatedProduct.getDescription());
                        product.setCategory(updatedProduct.getCategory());
                        return productRepository.save(product);
                }).orElseThrow(() -> new RuntimeException("Product not found"));
        }
}