package com.dev4j.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev4j.users.models.User;
import com.dev4j.users.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getListUsers(@RequestParam(required=false) String startWith){
		return new ResponseEntity<>(userService.getListUsers(startWith), HttpStatus.OK);
	}
	
	@GetMapping(value="/{nickname}")
	@ApiOperation(value="Return User by nickname")
	public ResponseEntity<User> getUser(@PathVariable String nickname){
		return new ResponseEntity<>(userService.getUser(nickname), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/{nickname}")
	@ApiOperation(value="Delete User by nickname")
	@ApiResponses(value= {
			@ApiResponse(code=204, message="Not Content")
	})
	public ResponseEntity<User> deleteUser(@PathVariable String nickname){
		userService.delete(nickname);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user){
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}
}
