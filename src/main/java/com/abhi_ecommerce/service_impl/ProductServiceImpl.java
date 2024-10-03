package com.abhi_ecommerce.service_impl;

import com.abhi_ecommerce.dto.CreateProductRequest;
import com.abhi_ecommerce.entity.Category;
import com.abhi_ecommerce.entity.Product;
import com.abhi_ecommerce.exception.ProductException;
import com.abhi_ecommerce.repository.CategoryRepository;
import com.abhi_ecommerce.response.ProductRepository;
import com.abhi_ecommerce.service.ProductService;
import com.abhi_ecommerce.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserService userService;
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              UserService userService){
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;

    }
    @Override
    public Product createProduct(CreateProductRequest createProductRequest) {
        Category topLevel = categoryRepository.findByName(createProductRequest.getTopLevelCategory());
        if(topLevel == null){
            Category topLevelCategory = new Category();
            topLevelCategory.setName(createProductRequest.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel = categoryRepository.save(topLevelCategory);
        }
        Category secondLevel = categoryRepository.findByNameAndParent(createProductRequest.getSecondLevelCategory(), topLevel.getName());
        if(secondLevel == null){
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(createProductRequest.getSecondLevelCategory());
            secondLevelCategory.setLevel(2);
            secondLevel = categoryRepository.save(secondLevelCategory);
        }
        Category thirdLevel = categoryRepository.findByNameAndParent(createProductRequest.getThirdLevelCategory(), secondLevel.getName());
        if(thirdLevel == null){
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(createProductRequest.getThirdLevelCategory());
            thirdLevelCategory.setLevel(3);
            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }
        Product product =new Product();
        product.setTitle(createProductRequest.getTitle());
        product.setColor(createProductRequest.getColor());
        product.setDescription(createProductRequest.getDescription());
        product.setDiscountedPrice(createProductRequest.getDiscountedPrice());
        product.setDiscountPercent(createProductRequest.getDisCountPercent());
        product.setImageUrl(createProductRequest.getImageUrl());
        product.setBrand(createProductRequest.getBrand());
        product.setPrice(createProductRequest.getPrice());
        product.setSizes(createProductRequest.getSize());
        product.setQuantity(createProductRequest.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public String deleteProduct(int productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.deleteById(productId);
        return "Product deleted Successfully";
    }

    @Override
    public Product updateProduct(int productId, Product productReq) throws ProductException {
        Product product = findProductById(productId);
        if(productReq.getQuantity() != 0){
            product.setQuantity(productReq.getQuantity());
        }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(int productId) throws ProductException {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            return product.get();
        }
        throw new ProductException("Product not found with id: "+productId);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
        if(!colors.isEmpty()){
            products = products.stream().filter(p->
                    colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }
        if(stock != null){
            if(stock.equals("In_stock")){
                products = products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            } else if (stock.equals("Out_of_stock")) {
                products = products.stream().filter(p->p.getQuantity() <1).collect(Collectors.toList());
            }
        }
        int startIndex = (int)pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filterProducts = new PageImpl<>(pageContent, pageable, products.size());
        return filterProducts;
    }
}
