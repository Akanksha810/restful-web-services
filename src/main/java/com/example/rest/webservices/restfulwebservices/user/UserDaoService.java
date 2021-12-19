package com.example.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

//To let spring manage it ----- use @repository since DAO connects to DB.
@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static List<Post> posts = new ArrayList<>();
	private static int userCount = 3;
	
	static {
		
		users.add(new User(1, "Akanksha", new Date()));
		users.add(new User(2, "Biswajit", new Date()));
		users.add(new User(3, "Chipmunk", new Date()));
		
		posts.add(new Post(1, "It's me, Akanksha!"));
		posts.add(new Post(1, "Hello There!"));
		posts.add(new Post(2, "It's me, Biswajit!"));
		posts.add(new Post(2, "Hi There!"));
		posts.add(new Post(2, "What's up?"));
		posts.add(new Post(3, "It's me, Chipmunk!"));
		posts.add(new Post(3, "Yo!"));
		posts.add(new Post(3, "Wassup?"));
		posts.add(new Post(3, "Bruh!"));
		
	}
	
	//Fetch All Users
	public List<User> getAllUsers() {
		
		return users;
		
	}
	
	//Save a New User
	public User saveUser(User user) {
		
		if(user.getId() == null) {		//duplicate id is possible in this case
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	//Find a Specific User
	public User findUser(int id) {
		
		for(User user : users) {
			
			if(user.getId() == id) {
				return user;
			}
		}
		
		return null;
	}

	public List<Post> getAllPosts(int id) {
		
		List<Post> userPosts = new ArrayList<>();
		
		for(Post post : posts) {
			
			if(post.getUserId() == id) {
				
				userPosts.add(post);
				
			}
			
		}
		
		return userPosts;
	}
	
}
