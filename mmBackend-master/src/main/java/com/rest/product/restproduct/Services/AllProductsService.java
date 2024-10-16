package com.rest.product.restproduct.Services;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rest.product.restproduct.dao.AllProductsRepository;
import com.rest.product.restproduct.entities.AllProducts;
import com.rest.product.restproduct.util.ImageUtils;

@Service
public class AllProductsService {
    
    
    @Autowired
    private AllProductsRepository allProductsRepository;

    public List<AllProducts> getAllProducts() {
        List<AllProducts> list =(List<AllProducts>)this.allProductsRepository.findAll();
        return list;
    }
    public AllProducts getProductById(int id){
        AllProducts product=null;
        // product = list.stream().filter(e->e.getArtistId()==id).findFirst().get();
        product = this.allProductsRepository.findById(id).orElse(null);
        return product;

    }

    public AllProducts addProduct(AllProducts b, MultipartFile image0, MultipartFile image1, MultipartFile image2) throws Exception{
        
        AllProducts  savedProduct = new AllProducts();
        savedProduct.setProductId(b.getProductId());
        savedProduct.setProductName(b.getProductName());
        savedProduct.setPrice(b.getPrice());
        // savedProduct.setQuantity(b.getQuantity());
        savedProduct.setArtistId(b.getArtistId());
        savedProduct.setArtistName(b.getArtistName());
        savedProduct.setData0(ImageUtils.compressImage(image0.getBytes()));
        savedProduct.setData1(ImageUtils.compressImage(image1.getBytes()));
        savedProduct.setData2(ImageUtils.compressImage(image2.getBytes()));
        savedProduct.setProductDescription(b.getProductDescription());
        savedProduct.setImageName(b.getProductName());
        savedProduct.setSubImageName1(b.getProductName()+'1');
        savedProduct.setSubImageName2(b.getProductName()+'2');
        savedProduct.setImageType(image0.getContentType());
        AllProducts  product = this.allProductsRepository.save(savedProduct);

        
        // AllProducts  product = this.allProductsRepository.save(b);
        return product;
        
    }

    public byte[] getImageByName0(String imageName){
        Optional<AllProducts> dbImageData = allProductsRepository.findByImageName(imageName);
        byte[] images= ImageUtils.decompressImage(dbImageData.get().getData0());
        return images;


    }
    public byte[] getImageByName1(String imageName){
        Optional<AllProducts> dbImageData = allProductsRepository.findBySubImageName1(imageName);
        byte[] images= ImageUtils.decompressImage(dbImageData.get().getData1());
        return images;


    }
    public byte[] getImageByName2(String imageName){
        Optional<AllProducts> dbImageData = allProductsRepository.findBySubImageName2(imageName);
        byte[] images= ImageUtils.decompressImage(dbImageData.get().getData2());
        return images;


    }
    


}
