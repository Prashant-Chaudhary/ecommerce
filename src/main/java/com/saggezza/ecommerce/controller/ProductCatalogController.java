package com.saggezza.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saggezza.ecommerce.model.Product;
import com.saggezza.ecommerce.request.ProductRequest;
import com.saggezza.ecommerce.request.RelatedQuestionRequest;
import com.saggezza.ecommerce.response.BaseResponse;
import com.saggezza.ecommerce.response.ProductCatalogListResponse;
import com.saggezza.ecommerce.service.ProductCatalogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/v1/catalog")
@Api(value = "Saggezza Catalog Resource")
public class ProductCatalogController {

	@Autowired
	protected ProductCatalogService productCatalogService;

	@PostMapping("/add-product")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "String", paramType = "header") })
	public BaseResponse addProduct(@RequestBody ProductRequest productRequest) {
		BaseResponse response = new BaseResponse();
		try {
			productCatalogService.addProduct(productRequest);
			setResponseStatus(response, 1, "Product Added / Updated Successfully");
		} catch (Exception ex) {
			setResponseStatus(response, 0, "Error Occured in Adding/Updating Product");
		}

		return response;
	}

	@GetMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "String", paramType = "header") })
	public ProductCatalogListResponse getProductCatalog() {
		ProductCatalogListResponse response = new ProductCatalogListResponse();
		setResponseStatus(response, 1, "Request Registered");
		List<Product> list = productCatalogService.getProductCatalog();
		try {
			if (list.size() > 0) {
				response.setProductList(list);
				setResponseStatus(response, 1, "Product Catalog List");
			} else {
				setResponseStatus(response, 0, "No Product List Available");
			}
		} catch (Exception ex) {
			setResponseStatus(response, 0, "Error Occured in Fetching Product Catalog List");
		}

		return response;
	}

		private static void setResponseStatus(BaseResponse response, int status, String message) {
		response.setMessage(message);
		response.setStatusCode(status);
	}
}
