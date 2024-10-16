package com.rest.product.restproduct.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.product.restproduct.Services.WishlistService;
import com.rest.product.restproduct.entities.Wishlist;


@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping  
    @CrossOrigin("**")
    public ResponseEntity<List<Wishlist>> getProducts() {
        List<Wishlist> list = wishlistService.getAllProducts();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
    // get single book
    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getProduct(@PathVariable("id") int id){
        Wishlist product = wishlistService.getProductById(id);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(product));

    }
    // add book
    
    @PostMapping
    public ResponseEntity<Wishlist> addProduct(@RequestBody Wishlist product){
          
        try {
            Wishlist b = this.wishlistService.addProduct(product);
            System.out.println(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wishlist> deleteProduct(@PathVariable("id") int id){
        try {
            this.wishlistService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}



