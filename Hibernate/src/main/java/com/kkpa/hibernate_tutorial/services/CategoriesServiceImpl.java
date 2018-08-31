package com.kkpa.hibernate_tutorial.services;

import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kkpa.hibernate_tutorial.model.Categories;
import com.kkpa.hibernate_tutorial.repositories.CategoriesRepository;
import com.kkpa.hibernate_tutorial.repositories.SubCategoriesRepository;

@Service
@Transactional
public class CategoriesServiceImpl {

  @Autowired
  private CategoriesRepository categoryRepository;

  @Autowired
  private SubCategoriesRepository subCategoryRepository;

  @PersistenceContext
  private EntityManager em;

  public CategoriesServiceImpl() {

  }

  public CategoriesServiceImpl(CategoriesRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public void createCategory(Categories category) {
    IntStream.range(0, 1000).forEach(i -> {
      category.setName(category.getName() + " - " + i);
      categoryRepository.save(category);
      category.getSubCategories().forEach(sub -> {
        subCategoryRepository.save(sub);
      });
    });


    applyingCriteriaAPI();

  }

  public void applyingCriteriaAPI() {

    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Categories> criteriaQuery = builder.createQuery(Categories.class);

    Root<Categories> root = criteriaQuery.from(Categories.class);
    criteriaQuery.select(root);

    TypedQuery<Categories> query = em.createQuery(criteriaQuery);
    query.getResultList().stream().forEach(System.out::println);


  }


}
