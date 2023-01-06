package com.dev4j.users.services;

import java.util.List;

import com.dev4j.users.models.User;

public interface UserService {

	public List<User> getListUsers(String startWith);
	
	public User getUser(String nickname);
	
	public User create(User user);
	
	public void delete(String nickname);
	
	public User update(User user);
}
