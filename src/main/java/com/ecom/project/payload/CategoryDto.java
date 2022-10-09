package com.ecom.project.payload;



public class CategoryDto {

	private int categoryid;
	private String categoryTitle;
	
	@Override
	public String toString() {
		return "CategoryDto [categoryid=" + categoryid + ", categoryTitle=" + categoryTitle + "]";
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
