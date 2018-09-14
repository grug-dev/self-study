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
import com.kkpa.hibernate_tutorial.dto.EmployeeDTO;
import com.kkpa.hibernate_tutorial.model.Categories;
import com.kkpa.hibernate_tutorial.model.SubCategories;
import com.kkpa.hibernate_tutorial.services.CategoriesServiceImpl;
import com.kkpa.hibernate_tutorial.services.EmployeeServiceImpl;

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
  CommandLineRunner init(CategoriesServiceImpl categoriesService,
      EmployeeServiceImpl employeeService) {
    return args -> {

      employeeTest(employeeService);

    };
  }

  private void findEmployees(EmployeeServiceImpl employeeService) {
    employeeService.findById(1l);

  }

  private void categoriesTest(CategoriesServiceImpl categoriesService) {

    Categories category = new Categories();
    category.setName("Category A");

    Set<SubCategories> subcategories = new HashSet<SubCategories>();
    SubCategories subcategory = new SubCategories();
    subcategory.setName("Sub Category A-1");
    subcategories.add(subcategory);
    category.setSubCategories(subcategories);

    categoriesService.createCategory(category);
  }

  private void employeeTest(EmployeeServiceImpl employeeService) {
    EmployeeDTO one = new EmployeeDTO(1l);
    EmployeeDTO bibi = new EmployeeDTO(2l);
    EmployeeDTO kmilo = new EmployeeDTO(3l);
    EmployeeDTO jose = new EmployeeDTO(4l);
    EmployeeDTO marlen = new EmployeeDTO(5l);

    kmilo.getColleagues().add(bibi.getEmployeeId());
    bibi.getColleagues().add(one.getEmployeeId());


    employeeService.save(one);
    employeeService.save(bibi);
    employeeService.save(kmilo);

    jose.getColleagues().add(one.getEmployeeId());
    marlen.getColleagues().add(one.getEmployeeId());

    employeeService.save(jose);
    employeeService.save(marlen);

  }



}
