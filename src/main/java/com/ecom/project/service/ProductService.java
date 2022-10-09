package com.ecom.project.service;



import com.ecom.project.payload.ProductResponse;
import com.ecom.project.payload.ProductDto;



public interface ProductService {
	//create Product
	
		ProductDto createProduct(ProductDto productDto,int categoryId);
		
		//update Product
		
		ProductDto updateProduct (ProductDto productDto, int productId);
		
		// delete Product
		
		void deleteProduct(int productId);
		
		//get single Product
		
		ProductDto getByProductId(int productId);
		
		//get All Product
		
		ProductResponse getAllProduct(Integer pageNumber,Integer pageSize,String sortby, String sortDir);
		
		//get Products BY Category
		
		

		ProductResponse getAllProductByCategory(Integer pageNumber, Integer pageSize, Integer categoryId);
		
		
		
		
		
}
