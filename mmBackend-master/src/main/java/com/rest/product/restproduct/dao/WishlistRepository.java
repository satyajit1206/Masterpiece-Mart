package com.rest.product.restproduct.dao;
import org.springframework.data.repository.CrudRepository;

import com.rest.product.restproduct.entities.Product;
import com.rest.product.restproduct.entities.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, Integer>{
    
}
