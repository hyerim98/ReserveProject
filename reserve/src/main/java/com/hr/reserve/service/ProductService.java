package com.hr.reserve.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.reserve.domain.Product;
import com.hr.reserve.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional
	public Long saveProduct(Product product) {
		Long id = productRepository.save(product);
		
		logger.info("register product[id : {}, productName : {}][{}]", id, product.getProductName(), this.getClass().getName());
		
		return id;
	}
	
	/*@Transactional
	public void updateProduct(Long productId, String name, int stockQuantity) {
		Product product = productRepository.findOne(productId);
		
		product.setStockQuantity(stockQuantity);
		product.setProductName(name);
	}*/
	
	public List<Product> findProducts() {
		
		logger.info("select products[{}]", this.getClass().getName());
		
		return productRepository.findAll();
	}
	
	public Product findOne(Long productId) {
		
		logger.info("find product by Id(PK)[Id : {}][{}]", productId, this.getClass().getName());
		
		return productRepository.findOne(productId);
	}

}
