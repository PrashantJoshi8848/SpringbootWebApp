package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProductEntities;

public interface ProductRepository extends JpaRepository<ProductEntities, Long> {

	List<ProductEntities> findAllBycategory_id(Object cateogyId);
}
