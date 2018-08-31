package com.kkpa.tutorial.service;

import org.springframework.stereotype.Service;
import com.kkpa.tutorial.domain.User;
import com.kkpa.tutorial.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {


  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getDomain(User user) {
    User usrNotFound = new User();
    usrNotFound.setName("User not found");
    return userRepository.findById(user.getId()).orElse(usrNotFound);
  }

  @Override
  public User create(User dto) {
    return userRepository.save(dto);
  }

}
