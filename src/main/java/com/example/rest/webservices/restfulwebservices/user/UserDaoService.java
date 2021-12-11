package com.example.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

//To let spring manage it ----- use @repository since DAO connects to DB.
@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	
	static {
		
		users.add(new User(1, "Akanksha", new Date()));
		users.add(new User(2, "Biswajit", new Date()));
		users.add(new User(3, "Chipmunk", new Date()));
		
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
	
}
