package com.ecom.project.service;

import java.util.List;

import com.ecom.project.payload.CategoryDto;


public interface CategoryService {

	
	//create new category
	 CategoryDto createCategory(CategoryDto categoryDto);
	
	//update new category
	 CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryid);
	
	
	//delete a category
	 void deleteCategory(Integer categoryid);
	
	//getting a single category
	 CategoryDto getCategorybyId(Integer categoryid);
	
	//getting all category
	List <CategoryDto> getallCategory();
}
