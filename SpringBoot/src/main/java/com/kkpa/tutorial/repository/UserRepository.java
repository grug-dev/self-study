package com.kkpa.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kkpa.tutorial.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
