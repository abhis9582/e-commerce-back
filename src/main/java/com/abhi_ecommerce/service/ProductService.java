package com.abhi_ecommerce.service;

import com.abhi_ecommerce.dto.CreateProductRequest;
import com.abhi_ecommerce.entity.Product;
import com.abhi_ecommerce.exception.ProductException;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest createProductRequest);
    public String deleteProduct(int productId) throws ProductException;
    public Product updateProduct(int productId, Product product) throws ProductException;
    public Product findProductById(int productId) throws ProductException;
    public List<Product> findProductByCategory(String category);
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer mainPrice, Integer maxPrice
    ,Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

}
