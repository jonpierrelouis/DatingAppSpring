package com.dating.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.models.Login;
import com.dating.repositories.LoginRepository;

@Service
public class LoginService {
	
	private final LoginRepository loginRepo;
	
	@Autowired
	public LoginService(LoginRepository loginRepo) {
		this.loginRepo = loginRepo;
	}
	
	///Methods
	
	/**
	 * Find a user's login info using the input email and password.
	 * @param email: The email of the user
	 * @param password: The password of the user
	 * @return The Optional Login object of the user
	 */
	public Optional<Login> loginUser(String email, String password){
		
		return loginRepo.findByUserEmailAndUserPassword(email, password);
	}
	
	public Login signUpUser(String username, String email, String password) {
		
		Login newUser = new Login(username, email, password);
		
		loginRepo.save(newUser);
		return newUser;
	}
	
}
