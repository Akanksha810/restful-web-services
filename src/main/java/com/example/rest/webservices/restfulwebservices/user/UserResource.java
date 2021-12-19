package com.example.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;

	//GET /users ---- Retrieve all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		
		return service.getAllUsers();
		
	}
	
	//GET /users/{id} ----- retrieve a specific user
	@GetMapping("/users/{id}")
	public User findUser(@PathVariable int id) {
		
		User findUser = service.findUser(id);
		
		if(findUser == null) {
			
			throw new UserNotFoundException("id : " + id);
			
		}
		
		return findUser;
		
	}
	
	//POST /user ---- Create a new user
	// input - user details
	// output - Status Code "CREATED" and created URI (eg. /user/{id})
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		
		User newUser = service.saveUser(user);
		
		// /users/{id}  --> /users/newUser.getId
		URI location = ServletUriComponentsBuilder					//not hard-coding /users
								.fromCurrentRequest()				
								.path("/{id}")   
								.buildAndExpand(newUser.getId())								  
								.toUri();	
		
		return ResponseEntity.created(location).build();
		
		//return service.saveUser(user);
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> allPosts(@PathVariable int id) {
		
		List<Post> posts = new ArrayList<>();
		
		posts = service.getAllPosts(id);
		
		return posts;
		
	}
	
}
