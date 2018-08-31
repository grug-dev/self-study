package com.kkpa.hibernate_tutorial.model;

import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
@Access(AccessType.FIELD)
public class Categories extends AuditModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_CATEGORY")
  private long id;

  @Basic
  @Column(name = "DESCRIPTION")
  private String name;


  @OneToMany
  @JoinColumn(name = "ID_CATEGORY", nullable = false)
  private Set<SubCategories> subCategories;

}
