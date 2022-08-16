package com.hr.reserve.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hr.reserve.domain.Product;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
	
	private final EntityManager em;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Long save(Product product) {
		if(product.getId() == null) {
			em.persist(product);
			logger.info("register product[id : {}, productName : {}][{}]", product.getId(), product.getProductName(), this.getClass().getName());
		}
		else {
			em.merge(product); // update
			logger.info("update product[id : {}, productName : {}][{}]", product.getId(), product.getProductName(), this.getClass().getName());
		}
		
		return product.getId();
	}
	
	
	public Product findOne(Long id) {
		
		logger.info("find product by Id(PK)[Id : {}][{}]", id, this.getClass().getName());
		
		return em.find(Product.class, id);
	}
	
	
	public List<Product> findAll() {
		String query = "select p from Product p";
		
		logger.info("select products[{}]", this.getClass().getName());
		
		return em.createQuery(query, Product.class).getResultList();
	}

}
