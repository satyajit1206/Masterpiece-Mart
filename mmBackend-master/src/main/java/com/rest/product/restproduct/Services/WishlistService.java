package com.rest.product.restproduct.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.product.restproduct.dao.ProductRepository;
import com.rest.product.restproduct.dao.WishlistRepository;
import com.rest.product.restproduct.entities.Wishlist;

@Service
public class WishlistService {

    
    @Autowired
    private WishlistRepository wishlistRepository;

    public List<Wishlist> getAllProducts() {
        List<Wishlist> list =(List<Wishlist>)this.wishlistRepository.findAll();
        return list;
    }
    public Wishlist getProductById(int id){
        Wishlist product=null;
        // product = list.stream().filter(e->e.getArtistId()==id).findFirst().get();
        product = this.wishlistRepository.findById(id).orElse(null);
        return product;

    }

    public Wishlist addProduct(Wishlist b){
        Wishlist  product = this.wishlistRepository.save(b);
        return product;
        
    }
    public void deleteProduct(int id) {
        wishlistRepository.deleteById(id);
        
    }

}
