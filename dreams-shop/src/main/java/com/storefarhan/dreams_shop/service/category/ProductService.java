package com.storefarhan.dreams_shop.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storefarhan.dreams_shop.model.Category;
import com.storefarhan.dreams_shop.exceptions.ProductNotFoundException;
import com.storefarhan.dreams_shop.model.Product;
import com.storefarhan.dreams_shop.repository.CategoriesRepository;
import com.storefarhan.dreams_shop.repository.ProductRepository;
import com.storefarhan.dreams_shop.request.AddProductRequest;
import com.storefarhan.dreams_shop.request.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements InterfaceProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        // TODO Auto-generated method stub
        Category category = Optional.ofNullable(categoriesRepository.findByName(request.getCategory().getName()))
        .orElseGet(() ->{
            Category newCategory = new Category(request.getCategory().getName());
            return categoriesRepository.save(newCategory);
        });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category){
        return new Product(request.getName(), request.getBrand(), request.getPrice(), request.getInventory(),  request.getDescription(),category);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        // TODO Auto-generated method stub
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public void deleteProductById(Long id) {
        // TODO Auto-generated method stub
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
        ()-> {
            throw new ProductNotFoundException("Product not found!");
        });
    }

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        return  productRepository.findAll();
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        // TODO Auto-generated method stub
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Product getProductById(Long id) {
        // TODO Auto-generated method stub
        return productRepository.findById(id).orElseThrow(
         ()->   new ProductNotFoundException("Product not found")
        );
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        // TODO Auto-generated method stub      38:59
        
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        // TODO Auto-generated method stub
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        // TODO Auto-generated method stub
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        // TODO Auto-generated method stub
        return productRepository.findByName(name);
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        // TODO Auto-generated method stub
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository :: save)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());

        Category category = categoriesRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }
}
