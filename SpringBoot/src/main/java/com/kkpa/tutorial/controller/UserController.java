package com.kkpa.tutorial.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kkpa.tutorial.broker.rabbitmq.Producer;
import com.kkpa.tutorial.domain.User;
import com.kkpa.tutorial.handle.UserNotFoundException;
import com.kkpa.tutorial.repository.UserRepository;
import com.kkpa.tutorial.service.UserService;

@RestController
@RequestMapping(Mappings.URI_USER)
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	private UserRepository userRepo;

	@Autowired
	private ApplicationContext appContext;

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
	public ResponseEntity<Resource<MappingJacksonValue>> getUser(
			@RequestHeader(name = HttpHeaders.ACCEPT) String accept, @PathVariable String id) {
		User user = userService.getDomain(new User(Integer.parseInt(id)));
		user.setName(user.getName() + accept);

		produceMessage("User not found!  Broker");

		if (user == null || user.getId() == null) {
			throw new UserNotFoundException("The user" + id + "doesnt exists");
		}

		Resource<MappingJacksonValue> resourceUser = createResourceHateOASofUser(user);

		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAll());

		resourceUser.add(linkTo.withRel("all-users"));

		return ResponseEntity.ok().body(resourceUser);
	}

	private Resource<MappingJacksonValue> createResourceHateOASofUser(User user) {
		// Dynamic Filtering
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("age");
		FilterProvider filterAge = new SimpleFilterProvider().addFilter("UserAgeFilter", propertyFilter);
		mapping.setFilters(filterAge);

		// HATEOAS
		Resource<MappingJacksonValue> resourceUser = new Resource<MappingJacksonValue>(mapping);

		return resourceUser;
	}

	@GetMapping("/caching/{id}")
	public ResponseEntity<Resource<MappingJacksonValue>> getUserCatched(@PathVariable String id) {
		log.info("Getting infor about user: " + id);
		User user = userService.getDomain(new User(Integer.parseInt(id)));

		Resource<MappingJacksonValue> resourceUser = createResourceHateOASofUser(user);

		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(resourceUser);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User userDTO) throws URISyntaxException {
		String msg = "Creating an User..: " + userDTO.toString();
		
		

		userDTO = userService.create(userDTO);
		URI location = new URI("/user/" + userDTO.getId());

		location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId())
				.toUri();

		produceMessage(msg);

		return ResponseEntity.created(location).body(userDTO);

	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		user.setId(Long.parseLong(id));
		User newUser = userRepo.save(user);
		return ResponseEntity.accepted().body(newUser);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<User> patchUser(@PathVariable String id, @RequestBody User user) {
		user.setId(Long.parseLong(id));
		User newUser = userRepo.save(user);
		return ResponseEntity.accepted().body(newUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		userRepo.delete(new User(Long.parseLong(id)));
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@GetMapping(value = "/sorting")
	public String sorting(@RequestParam("sortedBy") String sortedBy) {
		System.out.println("Sorting By: " + sortedBy);
		return "Applying Sorting! By: " + sortedBy;
	}

	@GetMapping(value = "/pagination")
	public String sorting(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {		
		return "Pagination with limit: " + limit + "offset: " + offset;
	}

	void produceMessage(String message) {
		try {
			Producer producer = appContext.getBean(Producer.class);
			producer.sendMessage(message);
		}catch (Exception e) {
			
		}
		
	}

}
