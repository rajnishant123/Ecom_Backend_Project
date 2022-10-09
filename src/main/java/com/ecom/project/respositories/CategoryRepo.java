package com.ecom.project.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.project.models.Category;




public interface CategoryRepo extends JpaRepository <Category,Integer> {

}
