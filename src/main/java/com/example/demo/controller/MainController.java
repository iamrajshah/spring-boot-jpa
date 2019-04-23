package com.example.demo.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/User")
public class MainController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/getAllUsers",method=RequestMethod.GET)
	public List<UserModel> getAllNotes() {
	    return userRepository.findAll();
	}
	
	@RequestMapping(value="/getUserById/{id}",method=RequestMethod.GET)
	public UserModel getUserById(@PathVariable(value="id") Long userId) {
	    return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User","id",userId));
	}
	
	@RequestMapping(value="/deleteUser/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User", "id", userId));
        
        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
    public UserModel createNote(@Valid @RequestBody UserModel user) {
        return userRepository.save(user);
    }
	
	@RequestMapping(value="/userModify/{id}", method=RequestMethod.PUT)
    public UserModel updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody UserModel userDetails) {

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User", "id", userId));

        user.setUserId(userDetails.getUserId());
        user.setUserName(userDetails.getUserName());

        UserModel updatedUser = userRepository.save(user);
        return updatedUser;
    }
	
}
