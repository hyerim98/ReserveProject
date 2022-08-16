package com.hr.reserve.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hr.reserve.domain.Cherry;
import com.hr.reserve.domain.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ProductServiceTest {
	
	@Autowired
	ProductService productService;
	
	@Test
	public void productSaveFindAll() {
		Product p1 = new Cherry();
		Product p2=  new Cherry();
		
		p1.setStockQuantity(100);
		p2.setStockQuantity(200);
		
		productService.saveProduct(p1);
		productService.saveProduct(p2);
		
		
		List<Product> products = productService.findProducts();
		
		for(Product p : products) {
			System.out.println("=== Àç°í : " + p.getStockQuantity());
		}
	}

}
