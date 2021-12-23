package com.example.rest.webservices.restfulwebservices.user;

public class Post {

	private int userId;
	private int post_id;
	private String message;
	
	public Post(int userId, int post_id, String message) {
		super();
		this.userId = userId;
		this.post_id = post_id;
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

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	@Override
	public String toString() {
		return "Post [userId=" + userId + ", post_id=" + post_id + ", message=" + message + "]";
	}
	
}
