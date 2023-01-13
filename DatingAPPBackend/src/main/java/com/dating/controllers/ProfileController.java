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
		
		Optional<Profile> optionalProfile = ps.getProfile((Integer)userId);
		
		//if a new account was created, a new profile is created
		if(!optionalProfile.isPresent()) {
			ps.saveNewProfile((Integer)userId);
			
			return getProfile(session, req);
		}
		
		return ResponseEntity.ok(optionalProfile.get());
	}

	/**
	 * Endpoint to change the user's name
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("edit/name")
	public ResponseEntity<Profile> changeName(HttpSession session, HttpServletRequest req){
		
		Object userId = session.getAttribute("userId");
		String newName = req.getParameter("newName");
		
		return ResponseEntity.ok(ps.changeName((Integer) userId, newName));
	}
	
	/**
	 * Endpoint to change the user's date of birth
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("edit/dob")
	public ResponseEntity<Profile> changeDOB(HttpSession session, HttpServletRequest req){
		
		Object userId = session.getAttribute("userId");
		int day = Integer.parseInt(req.getParameter("newDay"));
		int month = Integer.parseInt(req.getParameter("newMonth"));
		int year = Integer.parseInt(req.getParameter("newYear"));
		
		return ResponseEntity.ok(ps.changeDOB((Integer) userId, day, month, year));
	}
	
	/**
	 * Endpoint to change the user's location
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("edit/location")
	public ResponseEntity<Profile> changeLocation(HttpSession session, HttpServletRequest req){
		
		Object userId = session.getAttribute("userId");
		String city = req.getParameter("newCity");
		String state = req.getParameter("newState");
		
		return ResponseEntity.ok(ps.changeLocation((Integer) userId, city, state));
	}

	/**
	 * Endpoint to change the user's about yourself description
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("edit/aboutyourself")
	public ResponseEntity<Profile> changeAboutYourself(HttpSession session, HttpServletRequest req){
	
		Object userId = session.getAttribute("userId");
		String aboutYourself = req.getParameter("newAboutYourself");
		
		return ResponseEntity.ok(ps.changeAboutYourself((Integer) userId, aboutYourself));
	}

	/**
	 * Endpoint to change the user's gender
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("edit/gender")
	public ResponseEntity<Profile> changeGender(HttpSession session, HttpServletRequest req){
	
		Object userId = session.getAttribute("userId");
		String gender = req.getParameter("newGender");
		
		return ResponseEntity.ok(ps.changeGender((Integer) userId, gender));
	}

	/**
	 * Endpoint to change the user's sexual orientation
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("edit/orientation")
	public ResponseEntity<Profile> changeSexOriention(HttpSession session, HttpServletRequest req){
	
		Object userId = session.getAttribute("userId");
		String orientation = req.getParameter("newOrientation");
		
		return ResponseEntity.ok(ps.changeSexOrientation((Integer) userId, orientation));
	}
	
	/**
	 * Endpoint to add a like to the list
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("add/like")
	public ResponseEntity<Profile> addLike(HttpSession session, HttpServletRequest req){
		
		Object userId = session.getAttribute("userId");
		String like = req.getParameter("like");
		
		Profile profile = ps.addLike((Integer) userId, like);
		
		return ResponseEntity.ok(profile);
	}
	
	/**
	 * Endpoint to remove a like in their list
	 * @param session
	 * @param req
	 * @return
	 */
	@PostMapping("remove/like")
	public ResponseEntity<Profile> removeLike(HttpSession session, HttpServletRequest req){
		
		Object userId = session.getAttribute("userId");
		String like = req.getParameter("like");
		
		Profile profile = ps.removeLike((Integer) userId, like);
		
		return ResponseEntity.ok(profile);
	}
}
