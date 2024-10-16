package com.rest.product.restproduct.Services;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rest.product.restproduct.dao.ProductRepository;
import com.rest.product.restproduct.entities.Product;


import java.nio.file.Path;


@Service
public class ProductService {

    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> list =(List<Product>)this.productRepository.findAll();
        return list;
    }
    public Product getProductById(int id){
        Product product=null;
        // product = list.stream().filter(e->e.getArtistId()==id).findFirst().get();
        product = this.productRepository.findById(id).orElse(null);
        return product;

    }

    public Product addProduct(Product b){
        Product  product = this.productRepository.save(b);
        return product;
        
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        
            // Product exists, update its data
            Product existingProduct = optionalProduct.get();
            existingProduct.setProductName(updatedProduct.getProductName());
            // existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setArtistId(updatedProduct.getArtistId());
            existingProduct.setArtistName(updatedProduct.getArtistName());
            existingProduct.setProductId(updatedProduct.getProductId());
            // existingProduct.setProductImage(updatedProduct.getProductImage());
            existingProduct.setProductDescription(null);

            // Save the updated product
            return productRepository.save(existingProduct);
        
       

    }

    
    public void saveProductWithImage(Product product, MultipartFile file) throws Exception {
        // String fileName = file.getOriginalFilename();
        // Path path = Paths.get("src/main/resources/static/" + fileName);
        // Files.write(path, file.getBytes());

        Product savedProduct = new Product();
        savedProduct.setProductName(product.getProductName());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setQuantity(product.getQuantity());
        savedProduct.setArtistId(product.getArtistId());
        savedProduct.setArtistName(product.getArtistName());
        savedProduct.setProductId(product.getProductId());
        savedProduct.setProductDescription(product.getProductDescription());
        // savedProduct.setData(file.getBytes());

        

        // Save the image file path in the product
        

        // Save the product details to the database
        productRepository.save(savedProduct);
    }

}
