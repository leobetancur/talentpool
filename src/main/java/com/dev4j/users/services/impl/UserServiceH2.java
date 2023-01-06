package com.dev4j.users.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dev4j.users.entities.UserEntity;
import com.dev4j.users.models.User;
import com.dev4j.users.repositories.UserRepository;
import com.dev4j.users.services.UserService;
import com.github.javafaker.Faker;

@Service
@Profile("H2")
public class UserServiceH2 implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private Faker faker;
	
	@Override
	public List<User> getListUsers(String startWith) {
		List<UserEntity> listUserEntity;
		if(startWith==null) {
			listUserEntity = userRepository.findAll();
		}else {
			listUserEntity = userRepository.findByNickNameStartingWith(startWith);	
		}
	
		List<User> listReturn = new ArrayList<>();
		for(UserEntity userEntity:listUserEntity) {
			listReturn.add(mapper.map(userEntity, User.class));
		}
		return listReturn;
	}

	@Override
	@Cacheable("users")
	public User getUser(String nickname) {
		if(!userRepository.existsById(nickname)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s not found", nickname));
		}
		return mapper.map(userRepository.getById(nickname), User.class);
	}

	@Override
	public User create(User user) {
		UserEntity userEntity =userRepository.save(mapper.map(user, UserEntity.class));
		return mapper.map(userEntity, User.class);
	}

	@Override
	@CacheEvict("users")
	public void delete(String nickname) {
		userRepository.deleteById(nickname);
	
	}

	@Override
	public User update(User user) {
		if(!userRepository.existsById(user.getNickName())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s not found", user.getNickName()));
		}
		UserEntity userEntity =userRepository.save(mapper.map(user, UserEntity.class));
		return mapper.map(userEntity, User.class);
	}

	@PostConstruct
	public void init() {
		for(int i = 0 ; i<1000; i++) {
			userRepository.save(new UserEntity(faker.beer().name(), faker.artist().name(),  faker.backToTheFuture().character(), faker.number().randomDigit()));
		}
	}
}
