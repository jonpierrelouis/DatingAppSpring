package com.dating.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dating.models.Login;
import com.dating.services.LoginService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoginController {
	
	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	///Endpoints
	
	/**
	 * This method allows for the login of a user
	 * @param session
	 * @param req
	 * @return Returns the Login information of the user
	 */
	@PostMapping("/login")
	public ResponseEntity<Login> login(HttpSession session, HttpServletRequest req) {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Optional<Login> optionalLogin = loginService.loginUser(email, password);
		
		if(!optionalLogin.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		session.setAttribute("userId", optionalLogin.get().getUserId());
		
		return ResponseEntity.ok(optionalLogin.get());
	}
	
	/**
	 * This method allows for the creation of a new user
	 * @param session
	 * @param req
	 * @return Returns the created user's information
	 */
	@PostMapping("/register")
	public ResponseEntity<Login> signUp(HttpSession session, HttpServletRequest req){
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		//check to see if data exists
		//if it does send bad request
		Optional<Login> optionalLogin = loginService.loginUser(email, password);
		
		if(optionalLogin.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(loginService.signUpUser(username, email, password));
	}
	
	/**
	 * Method to allow a user to logout
	 * @param session
	 * @return Generic response entity holding no data
	 */
	public ResponseEntity<Void> logout(HttpSession session){
		session.removeAttribute("userId");
		
		return ResponseEntity.ok().build();
	}

}
