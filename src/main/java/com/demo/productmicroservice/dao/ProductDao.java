package com.demo.productmicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.productmicroservice.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

}
