package com.storefarhan.dreams_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storefarhan.dreams_shop.model.Category;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
