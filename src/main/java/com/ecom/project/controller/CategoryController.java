package com.ecom.project.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.project.payload.ApiRespons;
import com.ecom.project.payload.CategoryDto;
import com.ecom.project.service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	//Create new methods
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategoryDto =this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategoryDto,HttpStatus.CREATED);
		
	}
	//put -update
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{categoryId}")
public ResponseEntity<CategoryDto> updateCategory( @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer categoryid ){
		
		
		CategoryDto updatedCategory =this.categoryService.updateCategory(categoryDto,categoryid);
		return  ResponseEntity.ok(updatedCategory);
		
	}
	
	
	
	//delete
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiRespons>  deleteCategory(@PathVariable("categoryId")Integer categoryid){
		this.categoryService.deleteCategory(categoryid);
		
		return new ResponseEntity<ApiRespons>(new ApiRespons("Category Delted Succesfully",true),HttpStatus.OK);
	}
	
	//get -category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
	return ResponseEntity.ok(this.categoryService.getallCategory())	;
	}
	
	//get one category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
	return ResponseEntity.ok(this.categoryService.getCategorybyId(categoryId));
	}
	

}
