package com.kumar.backend.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.backend.Dao.ProductDao;
import com.kumar.backend.Util.AppConstant;
import com.kumar.backend.model.BaseProduct;
import com.kumar.backend.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Home Controller")
@RequestMapping("/product")
public class HomeController {

	@Autowired
	ProductDao productDao;
	
	@ApiOperation(value = "This API Return All the PRODUCT from DB",response = ArrayList.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = AppConstant.successMessage),
			@ApiResponse(code = 401, message = AppConstant.unauthorizedMessage),
			@ApiResponse(code = 403, message = AppConstant.forbiddenMessage),
			@ApiResponse(code = 400, message = AppConstant.notFoundMessage)
	})
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public List<String> getName(@PathVariable String id) {
		
		//TODO : Implement logger
		System.out.println("ID : "+id);
		List<String> names=new ArrayList<String>();
		names.add("Sample");
		names.add("Testing");
		names.add("Dummy");
		names.add("Data");
		
		return names;
	}
	
	@ApiOperation(value = "This API insert the PRODUCT to DB",response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = AppConstant.successMessage),
			@ApiResponse(code = 401, message = AppConstant.unauthorizedMessage),
			@ApiResponse(code = 403, message = AppConstant.forbiddenMessage),
			@ApiResponse(code = 400, message = AppConstant.notFoundMessage)
	})
	
	@PostMapping("/insert")
	public String insertProduct(@RequestBody(required = true) BaseProduct product) {
		
		System.out.println("Request body :"+product.toString());
		
		Product prod=new Product();
		
		prod.setItemId(product.getItemId());
		prod.setPrice(product.getPrice());
		prod.setCategory(product.getCategory());
		prod.setProductDescription(product.getProductDescription());
		prod.setProductName(product.getProductName());
			
		productDao.save(prod);
		
		return "Data Inserted Successfully";
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestBody(required = true) BaseProduct product) {
		System.out.println("Request body :"+product.getId());
		productDao.deleteById(product.getId());
		return "Data deleted Successfully";
	}
	
	@PutMapping("/update")
	public String updateProduct(@RequestBody(required = true) BaseProduct product) {
		System.out.println("Request body :"+product.getId());
		Optional<Product> tempProduct = productDao.findById(product.getId());
		Product p = tempProduct.get();
		Product prod=new Product();
		
		prod.setId(p.getId());
		prod.setItemId(product.getItemId());
		prod.setPrice(product.getPrice());
		prod.setCategory(product.getCategory());
		prod.setProductDescription(product.getProductDescription());
		prod.setProductName(product.getProductName());
		
	    productDao.save(prod);
		return "Data Updated Successfully";
	}
	
	@ApiOperation(value = "This API Return All the PRODUCT from DB",response = ArrayList.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = AppConstant.successMessage),
			@ApiResponse(code = 401, message = AppConstant.unauthorizedMessage),
			@ApiResponse(code = 403, message = AppConstant.forbiddenMessage),
			@ApiResponse(code = 400, message = AppConstant.notFoundMessage)
	})
	@GetMapping("/get/all")
	public List<Product> getAll(){
		System.out.println("Fetching All Product");
		return (List<Product>) productDao.findAll();
	}
	
	@PutMapping("/put/test")
	public void modelTest(@RequestBody BaseProduct prod) {
		System.out.println("Model : "+prod.toString());
	}
	
	
}
