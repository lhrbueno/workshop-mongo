package com.home.workshopmongo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.home.workshopmongo.domain.Post;
import com.home.workshopmongo.domain.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private String email;
	
	private List<Post> posts = new ArrayList<>();
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		posts = user.getPosts();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
