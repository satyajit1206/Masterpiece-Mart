package com.rest.product.restproduct.dao;
import org.springframework.data.repository.CrudRepository;

import com.rest.product.restproduct.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
    
}
