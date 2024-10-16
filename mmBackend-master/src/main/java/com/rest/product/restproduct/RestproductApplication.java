package com.rest.product.restproduct;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.rest.product.restproduct.dao.ProductRepository;
import com.rest.product.restproduct.entities.Product;

@SpringBootApplication
public class RestproductApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(RestproductApplication.class, args);
		ProductRepository productRepository= context.getBean(ProductRepository.class);
		// Product product = new Product(101, "rohan","water art", 2000.0f);
		// Product product = new Product(101, "Sarthak","water art", 2000.0f);

		// product.setArtistId(100);
		// product.setArtistName("sarthak");
		// product.setProductName("painting");
		// product.setPrice(1000.0f);
		// product.setArtistId(100);
		// product.setArtistName("sarthak");
		// product.setProductName("painting");
		// product.setPrice(1000.0f);
		// Product userResult = productRepository.save(product);
		// System.out.println(userResult);

	}

}
