package com.example.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public EntityModel<User> findUser(@PathVariable int id) {
		
		User findUser = service.findUser(id);
		
		if(findUser == null) {
			
			throw new UserNotFoundException("id : " + id);
			
		}
		
		EntityModel<User> model = EntityModel.of(findUser);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
		
	}
	
	//POST /user ---- Create a new user
	// input - user details
	// output - Status Code "CREATED" and created URI (eg. /user/{id})
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {		//@valid - for validating the request
		
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
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Post createdPost = service.savePost(id, post);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{post_id}")
							.buildAndExpand(createdPost.getPost_id())
							.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/users/{id}/posts/{post_id}")
	public Post findPost(@PathVariable int id, @PathVariable int post_id) {
		
		Post post = service.getPost(id, post_id);
		
		if(post == null) {
			
			throw new PostNotFoundException("User ID : " + id + " Post ID " + post_id);
			
		}
		
		return post;
		
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {		// void can also be used as for deletion, 200 OK is sufficient
		
		User user = service.deleteById(id);
		
		if(user == null) {
			
			throw new UserNotFoundException("User ID : " + id);
			
		}
		
		return ResponseEntity.noContent().build();		// STATUS : 204 - No Content  
		
	}
	
}
