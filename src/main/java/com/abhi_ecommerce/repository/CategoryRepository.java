package com.abhi_ecommerce.repository;

import com.abhi_ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);

    @Query(
            "Select c from Category c Where c.name= :name And c.parentCategory= :parentCategoryName"
    )
    Category findByNameAndParent(@Param("name") String name, @Param("parentCategoryName") String parentCategoryName);
}
