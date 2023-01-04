package com.dating.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.models.Login;
import com.dating.models.Profile;
import com.dating.repositories.ProfileRepository;

@Service
public class ProfileService {
	
	private final ProfileRepository pr;

	@Autowired
	public ProfileService(ProfileRepository pr) {
		this.pr = pr;
	}
	
	public Optional<Profile> getProfile(int userId) {
		return pr.findByLoginUserId(userId);
	}
	
	public Profile saveNewProfile(int userId) {
		
		Profile newProfile = new Profile();
		
		Login newLogin = new Login();
		newLogin.setUserId(userId);
		
		newProfile.setLogin(newLogin);
		System.out.println(userId);
		System.out.println(newProfile);
		
		return pr.save(newProfile);
	}

}
