package com.rest.product.restproduct.dao;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import com.rest.product.restproduct.entities.AllProducts;

public interface AllProductsRepository extends CrudRepository<AllProducts, Integer>{

    Optional<AllProducts> findByImageName(String imageName);
    Optional<AllProducts> findBySubImageName1(String imageName);
    Optional<AllProducts> findBySubImageName2(String imageName);
    
}
