package com.example.rest.webservices.restfulwebservices.user;

public class Post {

	private int userId;
	private String message;

	public Post(int userId, String message) {
		super();
		this.userId = userId;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Post [userId=" + userId + ", message=" + message + "]";
	}
	
}
