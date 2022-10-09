package com.ecom.project.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryid;
	
	@OneToMany(mappedBy ="category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Product> products= new HashSet<>();
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column(name="Title",nullable=false)
	private String categoryTitle;
	public Category(int categoryid, String categoryTitle) {
		super();
		this.categoryid = categoryid;
		this.categoryTitle = categoryTitle;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	
}
