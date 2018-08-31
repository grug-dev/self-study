package com.kkpa.tutorial.service;

public interface CrudService<K> {

  K getDomain(K dtoDomain);

  K create(K dto);

}
