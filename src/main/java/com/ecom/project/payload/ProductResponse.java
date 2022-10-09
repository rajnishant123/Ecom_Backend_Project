package com.ecom.project.payload;

import java.util.List;



public class ProductResponse {

	
	private List<ProductDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalrecords;
	private int totalPages;
	private boolean islast;
	@Override
	public String toString() {
		return "ProductResponse [content=" + content + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalrecords=" + totalrecords + ", totalPages=" + totalPages + ", islast=" + islast + "]";
	}

	public List<ProductDto> getContent() {
		return content;
	}
	
	public void setContent(List<ProductDto> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalrecords() {
		return totalrecords;
	}
	public void setTotalrecords(long totalrecords) {
		this.totalrecords = totalrecords;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isIslast() {
		return islast;
	}
	public void setIslast(boolean islast) {
		this.islast = islast;
	}
	
	
}
