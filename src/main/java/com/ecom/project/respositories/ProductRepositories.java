package com.ecom.project.respositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.project.models.Category;
import com.ecom.project.models.Product;


public interface ProductRepositories extends JpaRepository<Product,Integer>{
	
	Page<Product> findByCategory(Category category, Pageable pages);

}
