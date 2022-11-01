package com.dating.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.dating.models.Login;
import com.dating.services.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoginController {
	
	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	///Endpoints
	
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

}
