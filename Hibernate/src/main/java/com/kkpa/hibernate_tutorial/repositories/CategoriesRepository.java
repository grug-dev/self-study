package com.kkpa.hibernate_tutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kkpa.hibernate_tutorial.model.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}
