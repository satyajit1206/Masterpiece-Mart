package com.rest.product.restproduct.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.product.restproduct.Services.ProductService;
import com.rest.product.restproduct.entities.Product;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping  
    @CrossOrigin("**")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> list = productService.getAllProducts();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
        Product product = productService.getProductById(id);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(product));

    }
   
    
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
          
        try {
            System.out.println(product);
            Product b = this.productService.addProduct(product);
            System.out.println(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // @PostMapping
    // public ResponseEntity<?> uploadProductWithImage(
    //         @ModelAttribute Product product,
    //         @RequestParam("file") MultipartFile file) {
    //     try {
    //         productService.saveProductWithImage(product, file);
    //         return new ResponseEntity<>("Product and image uploaded successfully", HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>("Failed to upload product and image", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id){
        try {
            this.productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product){
        try {
            this.productService.updateProduct(id, product);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    

}



