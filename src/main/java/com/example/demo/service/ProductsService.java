package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.model.ProductEntities;
import com.example.demo.repository.ProductRepository;

@Component
public class ProductsService {
	@Autowired
	ProductRepository productRepository;
	
	public List<ProductEntities> getallproduct(){
		return productRepository.findAll();
	}
	public void Addproduct(ProductEntities productEntities) {
		productRepository.save(productEntities);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	public Optional<ProductEntities> updateproduct(Long id){
		return productRepository.findById(id);
	}
	
	public List<ProductEntities> getproductByCategoryId(int id){
		return productRepository.findAllBycategory_id(id);
	}

}
