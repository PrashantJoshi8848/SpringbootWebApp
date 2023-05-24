package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
