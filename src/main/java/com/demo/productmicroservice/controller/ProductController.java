package com.demo.productmicroservice.controller;

import java.util.List;

import java.util.Optional;

import com.demo.productmicroservice.dao.ProductDao;
import com.demo.productmicroservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

    @Autowired
    ProductDao productDao;

    @GetMapping(value = "/products")
    public List<Product> productsList(){
        List<Product> products = productDao.findAll();
        return products;
    }

    @GetMapping(value = "/products/{id}")
    public Optional<Product> getProduct(@PathVariable int id){
        Optional<Product> product = productDao.findById(id);
        return product;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        Product savedProduct = productDao.save(newProduct);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productDao.findById(id);

        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            // Update fields based on your requirements
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setDescription(updatedProduct.getDescription());
            productToUpdate.setPrice(updatedProduct.getPrice());

            // Save the updated product
            Product savedProduct = productDao.save(productToUpdate);
            return ResponseEntity.ok(savedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        if (productDao.existsById(id)) {
            productDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

