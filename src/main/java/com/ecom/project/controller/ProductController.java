package com.ecom.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.project.config.AppConstants;
import com.ecom.project.payload.ApiRespons;
import com.ecom.project.payload.ProductResponse;
import com.ecom.project.payload.ProductDto;
import com.ecom.project.service.FileUpload;
import com.ecom.project.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private FileUpload fileUpload;

	@Value("${product.images.path}")
	private String imagePath;

	// upload the file for product image

	@PostMapping("/products/images/{productId}")
	public ResponseEntity<?> uploadImageOfProduct(@PathVariable int productId,
			@RequestParam("product_image") MultipartFile file) {

		ProductDto product = this.productService.getByProductId(productId);
		String imageName = null;
		try {
			imageName = this.fileUpload.uploadFile(imagePath, file);
			product.setImageName(imageName);
			ProductDto productDto = this.productService.updateProduct(product, productId);
			return new ResponseEntity<>(productDto, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(Map.of("message", "File not uploaded on server !!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// get the image of given product
	@GetMapping("/products/images/{productId}")
	public void downloadImage(@PathVariable int productId, HttpServletResponse response) throws IOException {
		ProductDto product = this.productService.getByProductId(productId);
		String imageName = product.getImageName();
		String fullPath = imagePath + File.separator + imageName;
		InputStream resource = this.fileUpload.getResource(fullPath);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		OutputStream outputStream = response.getOutputStream();
		StreamUtils.copy(resource, outputStream);

	}

	// create Product
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/categories/{categoryId}/products")
	public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto, @PathVariable int categoryId) {

		ProductDto cretaedproduct = this.productService.createProduct(productDto, categoryId);

		return new ResponseEntity<ProductDto>(cretaedproduct, HttpStatus.CREATED);
	}

	// update Product
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/products/{productId}")
	public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable int productId) {

		ProductDto updatedUser = this.productService.updateProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(updatedUser, HttpStatus.OK);

	}

	// delete Product
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<ApiRespons> delete(@PathVariable int productId) {

		this.productService.deleteProduct(productId);

		return new ResponseEntity<ApiRespons>(new ApiRespons("User Deleted Successfully", true), HttpStatus.OK);

	}

	// get all Product

	@GetMapping("/products")
	public ResponseEntity<ProductResponse> getAllProduct(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER_STRING, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_STRING, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING, required = false) String sortDir) {
		ProductResponse all = this.productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<ProductResponse>(all, HttpStatus.OK);
	}

	// get single Product
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getSingleProduct(@PathVariable int productId) {
		ProductDto user = this.productService.getByProductId(productId);
		return new ResponseEntity<ProductDto>(user, HttpStatus.FOUND);
	}

	// get all products by Category

	@GetMapping("/categories/{categoryId}/products")
	public ResponseEntity<ProductResponse> getAllProductsByCategory(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER_STRING, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
			@PathVariable int categoryId) {
		ProductResponse all = this.productService.getAllProductByCategory(pageNumber, pageSize, categoryId);
		return new ResponseEntity<ProductResponse>(all, HttpStatus.OK);
	}

}
