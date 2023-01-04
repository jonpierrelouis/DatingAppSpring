package com.dating.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dating.models.Login;
import com.dating.models.Profile;
import com.dating.services.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProfileController {
	
	private final ProfileService ps;

	@Autowired
	public ProfileController(ProfileService ps) {
		this.ps = ps;
	}
	
	/**
	 * Gets all the information of the user's profile
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("myProfile")
	public ResponseEntity<Profile> getProfile(HttpSession session, HttpServletRequest req){
		
		Object userId = session.getAttribute("userId");
		
		System.out.println(userId +" Data Type: ");
		
		Optional<Profile> optionalProfile = ps.getProfile((Integer)userId);
		
		//if a new account was created, a new profile is created
		if(!optionalProfile.isPresent()) {
			ps.saveNewProfile((Integer)userId);
			
//			return ResponseEntity.badRequest().build();
			return getProfile(session, req);
		}
		
		
		return ResponseEntity.ok(optionalProfile.get());
	}

}
