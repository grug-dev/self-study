package com.kkpa.tutorial.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.kkpa.tutorial.domain.User;
import com.kkpa.tutorial.repository.UserRepository;
import com.kkpa.tutorial.service.UserService;

@RestController(Mappings.URI_USER)
public class UserController {

  private Logger log = LoggerFactory.getLogger(UserController.class);

  private UserService userService;

  private UserRepository userRepo;



  public UserController(UserService userService, UserRepository userRepo) {
    this.userService = userService;
    this.userRepo = userRepo;
  }

  @GetMapping("/")
  public ResponseEntity<List<User>> getAll() {
    List<User> allUsers = userRepo.findAll();
    return ResponseEntity.ok(allUsers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable int id) {
    User user = userService.getDomain(new User(id));
    return ResponseEntity.ok().body(user);
  }

  @GetMapping("/caching/{id}")
  public ResponseEntity<User> getUserCatched(@PathVariable int id) {
    log.info("Getting infor about user: " + id);
    User user = userService.getDomain(new User(id));
    return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(user);
  }

  @PostMapping("/")
  public ResponseEntity<User> createUser(@RequestBody User userDTO) throws URISyntaxException {
    log.info("Creating an User..: " + userDTO.toString());

    userDTO = userService.create(userDTO);
    URI location = new URI("/user/" + userDTO.getId());

    location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(userDTO.getId()).toUri();

    return ResponseEntity.created(location).body(userDTO);

  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
    user.setId(id);
    User newUser = userRepo.save(user);
    return ResponseEntity.accepted().body(newUser);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> patchUser(@PathVariable long id, @RequestBody User user) {
    user.setId(id);
    User newUser = userRepo.save(user);
    return ResponseEntity.accepted().body(newUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {
    userRepo.delete(new User(id));
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @GetMapping(value = "/sorting")
  public String sorting(@RequestParam("sortedBy") String sortedBy) {
    System.out.println("Sorting By: " + sortedBy);
    return "Applying Sorting!";
  }

  @GetMapping(value = "/pagination")
  public String sorting(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
    System.out.println("Pagination with limit: " + limit + "offset: " + offset);
    return "Applying Sorting!";
  }


}
