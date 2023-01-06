package com.dev4j.users.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dev4j.users.models.User;
import com.dev4j.users.services.UserService;
import com.github.javafaker.Faker;

@Service
@Profile("Faker")
public class UserServiceFaker implements UserService{

	private List<User> usersList;
	private static final int CUANTITY = 10;
	
	@Autowired
	private Faker faker;
	
	@PostConstruct
	public void init() {
		usersList = new ArrayList<>();
		for(int i=0 ; i<CUANTITY - 1 ; i++) {
			usersList.add(new User(faker.ancient().hero(), faker.gameOfThrones().character(),faker.funnyName().name(), 1));
		}
			
	}
	
	@Override
	public List<User> getListUsers(String startWith) {
		if(startWith==null) {
			return usersList;
		}
		
		return usersList.stream()
				.filter(user->user.getNickName().startsWith(startWith))
				.collect(Collectors.toList());
	}

	@Override
	public User getUser(String nickname) {
		return usersList.stream()
			.filter(user->nickname.equals(user.getNickName())).findAny()
			.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s not found", nickname)));
	}

	@Override
	public User create(User user) {
		usersList.add(user);
		return user;
	}

	@Override
	public void delete(String nickname) {
		User user = getUser(nickname);
		usersList.remove(user);
		
	}

	@Override
	public User update(User user) {
		User userUpdated = getUser(user.getNickName());
		userUpdated.setNickName(user.getNickName());
		userUpdated.setFirstName(user.getFirstName());
		userUpdated.setSecondName(user.getSecondName());
		return userUpdated;
	}

}
