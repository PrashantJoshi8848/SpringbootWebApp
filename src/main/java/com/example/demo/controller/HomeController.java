package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductsService;


@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductsService productsService;

	
	@GetMapping({"/","/home"})
	public String getHome() {
		return "index";
	}
	@GetMapping("/shop")
	public String getproducts(Model model) {
	model.addAttribute("categories",categoryService.getAllcategory());
	model.addAttribute("products",productsService.getallproduct());
		return "shop";
	}
	@GetMapping("/shop/category/{id}")
	public String filterproduct(@PathVariable("id") int id, Model model) {
		model.addAttribute("categories",categoryService.getAllcategory());
		model.addAttribute("products",productsService.getproductByCategoryId(id));
		return "shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String getSingleProduct(@PathVariable("id") Long id,Model model) {
		model.addAttribute("product",productsService.updateproduct(id).get());
		return "viewProduct";
	}
}
