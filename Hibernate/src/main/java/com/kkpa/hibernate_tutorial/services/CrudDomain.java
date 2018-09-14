package com.kkpa.hibernate_tutorial.services;

import java.util.Optional;

public interface CrudDomain<T> {

  Optional<T> findById(Long id);

  T save(T object);

}
