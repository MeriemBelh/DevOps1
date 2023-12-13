package com.demo.productmicroservice.controller;

import com.demo.productmicroservice.dao.ProductDao;
import com.demo.productmicroservice.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.log.SystemLogHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductDao productDao;

    ObjectMapper mapper = new ObjectMapper();
    String resultContent = null;


    @Test
    public void findAllProductsTest() throws Exception{
        List<Product> products = Arrays.asList(
                new Product("product 1", "product 1 description", 400.0),
                new Product("product 2", "product 2 description", 500.0)
        );

        resultContent = mapper.writeValueAsString(products);
        System.out.println(resultContent);
        when(productDao.findAll()).thenReturn(products);


        mvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString(resultContent)))
                        .andDo(print());

    }

    @Test
    public void getProductsByIdTest() throws Exception {

        Product p = new Product("product 1", "product 1 description", 400.0);
        p.setId(100);
        resultContent = mapper.writeValueAsString(p);
        when(productDao.findById(100)).thenReturn(Optional.of(p));

        this.mvc.perform(get("/products/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(resultContent)))
                .andDo(print());
    }

    @Test
    public void createProductTest() throws Exception {
        Product p = new Product("product 1", "product 1 description", 400.0);
        p.setId(101);
        String product = mapper.writeValueAsString(p);
        when(productDao.save(Mockito.any(Product.class))).thenReturn(p);

        this.mvc.perform((post("/products"))
                        .content(product)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void updateProductTest() throws Exception {
        Product p = new Product("product 1", "product 1 description", 400.0);
        String product = mapper.writeValueAsString(p);
        when(productDao.findById(2)).thenReturn(Optional.of(p));

        this.mvc.perform((put("/products/2"))
                        .content(product)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteProductTest() throws Exception {
        Product p = new Product("product 1", "product 1 description", 400.0);
        p.setId(102);

        // Mocking the behavior when trying to delete a product with ID 102
        when(productDao.existsById(102)).thenReturn(true);

        this.mvc.perform(delete("/products/102")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andDo(print());
    }
}