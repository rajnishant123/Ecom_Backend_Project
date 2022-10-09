package com.ecom.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ecom.project.exception.ResourceNotFoundException;
import com.ecom.project.models.Category;
import com.ecom.project.models.Product;
import com.ecom.project.payload.ProductResponse;
import com.ecom.project.payload.ProductDto;
import com.ecom.project.respositories.CategoryRepo;
import com.ecom.project.respositories.ProductRepositories;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepositories productRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Override
	public ProductDto createProduct(ProductDto productDto,int categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.catRepo.findById(categoryId).
				orElseThrow(() -> new ResourceNotFoundException("Category Not Found"+categoryId));
	
		Product p=this.toEntity(productDto);
		p.setCategory(cat);
		
		
		Product createdProduct=this.productRepo.save(p);
		
		return this.toDto(createdProduct);
	}

	public ProductDto toDto(Product createdProduct) {
		// TODO Auto-generated method stub
		ProductDto pd=this.modelMapper.map(createdProduct, ProductDto.class);
				return pd;
	}

	public  Product toEntity(ProductDto productDto) {
		// TODO Auto-generated method stub
		Product p= this.modelMapper.map(productDto,Product.class);
		return p;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, int productId) {
		// TODO Auto-generated method stub
		Product p=this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"+productId));
		p.setProductDesc(productDto.getProductDesc());
		p.setProductName(productDto.getProductName());
		p.setStock(productDto.isStock());
		p.setLive(productDto.isLive());
		p.setImageName(productDto.getImageName());
		p.setProductPrice(productDto.getProductPrice());
		Product updatep=this.productRepo.save(p);
		return this.toDto(updatep);
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		Product p=this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"+productId));
		this.productRepo.delete(p);
		
	}

	@Override
	public ProductDto getByProductId(int productId) {
		// TODO Auto-generated method stub
		Product p=this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"+productId));
		return this.toDto(p);
	}

	@Override
	public ProductResponse getAllProduct(Integer pageNumber,Integer  pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		Sort sort=null;
		if(sortDir.trim().toLowerCase().equals("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}

		Pageable pages= PageRequest.of(pageNumber, pageSize,sort);
		Page <Product> pageProduct=this.productRepo.findAll(pages);
		List<Product> p=pageProduct.getContent();
		List<ProductDto> allProduct=p.stream().map(pr-> this.toDto(pr)).collect(Collectors.toList());
		
		ProductResponse productRes= new ProductResponse();
		productRes.setContent(allProduct);
		System.out.println("printing productres"+productRes);
		productRes.setPageNumber(pageProduct.getNumber());
		productRes.setPageSize(pageProduct.getSize());
		productRes.setTotalrecords(pageProduct.getTotalElements());
		productRes.setTotalPages(pageProduct.getTotalPages());
		productRes.setIslast(pageProduct.isLast());
		return productRes;
	}

	

	@Override
	public ProductResponse getAllProductByCategory(Integer pageNumber,Integer  pageSize,Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.catRepo.findById(categoryId).
				orElseThrow(() -> new ResourceNotFoundException("Category Not Found"+categoryId));
		Pageable pages= PageRequest.of(pageNumber, pageSize);
		Page <Product> pageCategoryProduct=this.productRepo.findByCategory(cat,pages);
		List<Product> categories=pageCategoryProduct.getContent();
		List<ProductDto> allProduct=categories.stream().map(pr-> this.toDto(pr)).collect(Collectors.toList());
		ProductResponse productRes= new ProductResponse();
		productRes.setContent(allProduct);
		productRes.setPageNumber(pageCategoryProduct.getNumber());
		productRes.setPageSize(pageCategoryProduct.getSize());
		productRes.setTotalrecords(pageCategoryProduct.getTotalElements());
		productRes.setTotalPages(pageCategoryProduct.getTotalPages());
		productRes.setIslast(pageCategoryProduct.isLast());
	;
		return productRes;
		
	}



}
