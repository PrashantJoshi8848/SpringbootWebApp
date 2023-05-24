package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Category;
import com.example.demo.model.ProductEntities;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductsService;

//import com.example.demo.model.Category;

@Controller
@RequestMapping("/admin")
public class AdminController {
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductsService productsService;
	
	@GetMapping("/home")
	public String admin() {
		return "adminHome";
	}
	
	@GetMapping("/categories")
	public String adminCategories(Model model){	
		model.addAttribute("categories",categoryService.getAllcategory());
		return "categories";
	}
	
	@GetMapping("/categories/add")
	public String getCartAdd(Model model) {
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/categories/add")
	public String postCartAdd(@ModelAttribute("category")Category category) {
			categoryService.addCategory(category);
		return "redirect:/admin/categories";
		
	}
	
	@GetMapping("/categories/delete/{id}")
	public String deleteSingleItems(@PathVariable("id")int id) {
		categoryService.deleteSingleProduct(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/categories/update/{id}")
	public String updateSingleItems(@PathVariable("id")int id,Model model)
	{
		if(!categoryService.checkupdateid(id).isEmpty()) {			
			model.addAttribute("category",categoryService.checkupdateid(id).get());
			return "categoriesAdd";
		}
		return "notfound";
	}
	
//	products Controller
	
	@GetMapping("/products")
	public String Products(Model model) {
		model.addAttribute("products",productsService.getallproduct());
		return "products";
	}
	
//	product postProduct
	@GetMapping("/products/add")
	public String addProducts(Model model) {
		model.addAttribute("ProductDetail","Add new Product");
		model.addAttribute("productDTO",new ProductDTO());
		model.addAttribute("categories",categoryService.getAllcategory());
		return "productsAdd";
	}
	@PostMapping("/products/add")
	public String addProducts(@ModelAttribute("productDTO") ProductDTO productDTO,@RequestParam("productImage")MultipartFile file,@RequestParam("imgName")String imgname)throws IOException {
		ProductEntities productEntities=new ProductEntities();
		productEntities.setId(productDTO.getId());
		productEntities.setCategory(categoryService.getSingleCategory(productDTO.getCategoryId()).get());
		productEntities.setDescription(productDTO.getDescription());
		productEntities.setName(productDTO.getName());
		productEntities.setPrice(productDTO.getPrice());
		productEntities.setWeight(productDTO.getWeight());
		
		String UUID;
		if(!file.isEmpty()) {
			UUID=file.getOriginalFilename();
			Path fileNameAndPath=Paths.get(uploadDir,UUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			UUID= imgname;
		}
		productEntities.setImageName(UUID);
		
		productsService.Addproduct(productEntities);
		
		
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id")Long id) {
		productsService.deleteProduct(id);			
		return "redirect:/admin/products";
	}
	
	@GetMapping("/product/update/{id}")
	public String updateProduct(@PathVariable("id")Long id,Model model) {
			ProductEntities productEntities=productsService.updateproduct(id).get();
			ProductDTO productDTO=new ProductDTO();
			productDTO.setId(productEntities.getId());
			productDTO.setName(productEntities.getName());
			productDTO.setDescription(productEntities.getDescription());
			productDTO.setPrice(productEntities.getPrice());
			productDTO.setWeight(productEntities.getWeight());
			productDTO.setImageName(productEntities.getImageName());
			productDTO.setCategoryId(productEntities.getCategory().getId());
			model.addAttribute("ProductDetail","Update Product");
			model.addAttribute("categories", categoryService.getAllcategory());
			model.addAttribute("productDTO", productDTO);
			return "productsAdd";		
	}
	
	
	
	

}
