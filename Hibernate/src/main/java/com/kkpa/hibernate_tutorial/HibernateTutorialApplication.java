package com.kkpa.hibernate_tutorial;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.kkpa.hibernate_tutorial.model.Categories;
import com.kkpa.hibernate_tutorial.model.SubCategories;
import com.kkpa.hibernate_tutorial.services.CategoriesServiceImpl;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
@EnableAutoConfiguration
public class HibernateTutorialApplication {

  @Autowired
  private Environment env;

  public static void main(String[] args) {
    SpringApplication.run(HibernateTutorialApplication.class, args);
  }

  @Bean
  CommandLineRunner init(CategoriesServiceImpl categoriesService) {
    return args -> {
      Categories category = new Categories();
      category.setName("Category A");

      Set<SubCategories> subcategories = new HashSet<SubCategories>();
      SubCategories subcategory = new SubCategories();
      subcategory.setName("Sub Category A-1");
      subcategories.add(subcategory);
      category.setSubCategories(subcategories);

      categoriesService.createCategory(category);
    };
  }



}
