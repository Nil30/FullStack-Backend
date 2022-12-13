package com.codewithnilesh.FullStackBackend.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewithnilesh.FullStackBackend.exception.UserNotFoundException;
import com.codewithnilesh.FullStackBackend.model.User;
import com.codewithnilesh.FullStackBackend.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepository userrepository;
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userrepository.save(newUser);
	}
	
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userrepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUserByid(@PathVariable Long id) {
		return userrepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable Long id) {
		return userrepository.findById(id)
				.map(user->{
			user.setUsername(newUser.getUsername());
			user.setUsername(newUser.getName());
			user.setUsername(newUser.getEmail());
			return userrepository.save(user);
			
		}).orElseThrow(()-> new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userrepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userrepository.deleteById(id);
		return "User Has been deleted "+ id +" Done";
		
		
	}
	
}
