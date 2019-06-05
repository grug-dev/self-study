package com.kkpa.tutorial.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kkpa.tutorial.domain.User;

@RestController
@RequestMapping("/restemplate")
public class RestTemplateController {

	private Logger log = LoggerFactory.getLogger(RestTemplateController.class);

	//@Autowired
	private RestTemplate rest;

	@GetMapping("/sortingUserController")
	public String getUserControllerSorting() {
		return rest.getForObject("http://localhost:9999/mytutorial/user/sorting?sortedBy={sortField}", String.class,
				"Sorte by Rest Template");

	}

	@GetMapping("/paginationUserController")
	public String getUserControllerPagination() {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("limit", "5");
		urlVariables.put("offset", "20");
		return rest.getForObject("http://localhost:9999/mytutorial/user/pagination?limit={limit}&offset={offset}",
				String.class, urlVariables);
	}

	@PostMapping("/createUser")
	public User createUser() {
		User user = new User();
		user.setAge(24);
		user.setBirthDate(LocalDate.now());
		user.setId(8l);
		user.setName("CristianCamiloPeñaAlvarez");

		ResponseEntity<User> responseEntity = rest.postForEntity("http://localhost:9999/mytutorial/user", user, User.class);

		log.info("New resource created at " + responseEntity.getHeaders().getLocation());
		return responseEntity.getBody();
	}

}
