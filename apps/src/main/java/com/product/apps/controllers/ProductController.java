package com.product.apps.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.apps.model.Product;
import com.product.apps.service.ProductService;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    public ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // @GetMapping
    // public Product getProduct(@RequestParam String name) {
    //     return  productService.getProductByName(name);
    // }
@GetMapping
public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getEveryProduct();
    if (products.isEmpty()) {
        return ResponseEntity.noContent().build(); // returns 204 No Content
    }
    return ResponseEntity.ok(products);
}

   @PostMapping
   public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(Response.SC_CREATED).body(savedProduct);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    
    
}