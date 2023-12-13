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



}

