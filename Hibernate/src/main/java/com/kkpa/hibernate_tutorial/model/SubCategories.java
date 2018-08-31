package com.kkpa.hibernate_tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sub_categories")
public class SubCategories extends AuditModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_SUBCATEGORIES")
  private long idSubCategory;

  @Column(name = "nombre")
  private String name;
}
