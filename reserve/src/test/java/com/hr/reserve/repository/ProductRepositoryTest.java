package com.hr.reserve.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hr.reserve.domain.CWeight;
import com.hr.reserve.domain.Cherry;
import com.hr.reserve.domain.Product;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductRepositoryTest {
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Transactional
	public void save() {
		Cherry product = new Cherry();
		
		product.setProductName("c500");
		product.setStockQuantity(100);
		product.setWeight(CWeight.G500);
		
		Long saveId = productRepository.save(product);
		
		Product findProduct = productRepository.findOne(saveId);
		
		Assertions.assertThat(saveId).isEqualTo(findProduct.getId());
	}
	
	

}
