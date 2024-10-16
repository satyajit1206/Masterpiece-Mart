package com.rest.product.restproduct.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class AllProducts {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;
    private int artist_id;
    private String artist_name;
    private String product_name;
    private float price;
    private String imageName;
    private String subImageName1;
    private String subImageName2;
    private String imagetype;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] product_image;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] sub_image1;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] sub_image2;
    
    @Column(length = 5000)
    private String product_description;

    // add default constructor
    public AllProducts() {
    }


    public AllProducts(int artist_id, String artist_name,String product_name, float price, long product_id, String product_image, String product_description) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.product_name = product_name;
        this.price = price;
        this.product_id = product_id;
        // this.product_image = product_image;
        this.product_description = product_description;

    }

    public int getArtistId() {
        return artist_id;
    }

    public void setArtistId(int artist_id) {
        this.artist_id = artist_id;
    }
    public long getProductId() {
        return product_id;
    }
    public void setProductId(long product_id){
        this.product_id = product_id;
    }
    public String getArtistName() {
        return artist_name;
    }

    public void setArtistName(String artist_name) {
        this.artist_name = artist_name;
    }
    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    // public String getData() {
    //     return product_image;
    // }
    public void setData0(byte[] product_image) {
        this.product_image = product_image;
    }
    public byte[] getData0(){
        return this.product_image;
    }
    public void setData1(byte[] product_image) {
        this.sub_image1 = product_image;
    }
    public byte[] getData1(){
        return this.sub_image1;
    }
    public void setData2(byte[] product_image) {
        this.sub_image2 = product_image;
    }
    public byte[] getData2(){
        return this.sub_image2;
    }
    public String getProductDescription() {
        return product_description;
    }
    public void setProductDescription(String product_description) {
        this.product_description = product_description;
    }
    
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = product_name;
    }
    public String getSubImageName1() {
        return subImageName1;
    }
    public void setSubImageName1(String subImageName1) {
        this.subImageName1 = subImageName1;
    }
    public String getSubImageName2() {
        return subImageName2;
    }
    public void setSubImageName2(String subImageName2) {
        this.subImageName2 =subImageName2;
    }
    public String getImageType() {
        return imagetype;
    }
    public void setImageType(String imagetype) {
        this.imagetype = imagetype;
    }
}
