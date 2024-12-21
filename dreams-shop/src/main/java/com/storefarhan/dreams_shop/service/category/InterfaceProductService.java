package com.storefarhan.dreams_shop.service.category;

import java.util.List;

import com.storefarhan.dreams_shop.model.Product;
import com.storefarhan.dreams_shop.request.AddProductRequest;
import com.storefarhan.dreams_shop.request.ProductUpdateRequest;

public interface InterfaceProductService {

    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest request, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
