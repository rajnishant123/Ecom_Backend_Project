package com.ecom.project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;

	private String productName;
	private boolean Live;
	private String imageName;

	private String productDesc;

	private boolean stock;

	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;

	@Column(name = "Quantity")
	private int productQuantity;
	private double productPrice;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Product(int productId, String productName, boolean live, String imageName, Category category,
			int productQuantity, double productPrice, String productDesc, boolean stock) {
		super();
		this.productId = productId;
		this.productName = productName;
		Live = live;
		this.imageName = imageName;
		this.category = category;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.productDesc = productDesc;
		this.stock = stock;
	}

	public boolean isLive() {
		return Live;
	}

	public void setLive(boolean Live) {
		this.Live = Live;
	}

	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", Live=" + Live + ", imageName="
				+ imageName + ", category=" + category + ", productQuantity=" + productQuantity + ", productPrice="
				+ productPrice + ", productDesc=" + productDesc + ", stock=" + stock + "]";
	}

	public Product(int productId, String productName, double productPrice, String productDesc, boolean stock) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDesc = productDesc;
		this.stock = stock;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

}
