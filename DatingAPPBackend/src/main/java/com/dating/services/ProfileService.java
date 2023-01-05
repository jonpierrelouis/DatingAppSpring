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
	
	/**
	 * Method to be called when a new user creates a profile for the first time
	 * @param userId
	 * @return
	 */
	public Profile saveNewProfile(int userId) {
		//make a new profile and login model to add to the database
		Profile newProfile = new Profile();
		
		Login newLogin = new Login();
		newLogin.setUserId(userId);
		
		newProfile.setLogin(newLogin);
		System.out.println(userId);
		System.out.println(newProfile);
		
		return pr.save(newProfile);
	}

	/**
	 * Function to change the name of the user
	 * @param userId
	 * @param newName
	 * @return
	 */
	public Profile changeName(int userId, String newName) {
		
		Optional<Profile> profile = pr.findByLoginUserId(userId);
		
		profile.get().setName(newName);
		
		return pr.save(profile.get());
	}
	
	/**
	 * Function to change the birthday of the user
	 * @param userId
	 * @param newBirthDay
	 * @param newBirthMonth
	 * @param newBirthYear
	 * @return
	 */
	public Profile changeDOB(int userId, int newBirthDay, int newBirthMonth, int newBirthYear) {
		
		Optional<Profile> profile = pr.findByLoginUserId(userId);
		
		profile.get().setBirthDay(newBirthDay);
		profile.get().setBirthMonth(newBirthMonth);
		profile.get().setBirthYear(newBirthYear);
		
		return pr.save(profile.get());
	}
	
	/**
	 * Function to change the location of the user
	 * @param userId
	 * @param newCity
	 * @param newState
	 * @return
	 */
	public Profile changeLocation(int userId, String newCity, String newState) {
		
		Optional<Profile> profile = pr.findByLoginUserId(userId);
		
		profile.get().setCity(newCity);
		profile.get().setState(newState);
		
		return pr.save(profile.get());
	}
	
	/**
	 * Function to change the about yourself of the user
	 * @param userId
	 * @param newAboutYourself
	 * @return
	 */
	public Profile changeAboutYourself(int userId, String newAboutYourself) {
		
		Optional<Profile> profile = pr.findByLoginUserId(userId);
		
		profile.get().setAboutYourself(newAboutYourself);
		
		return pr.save(profile.get());
	}
	
	/**
	 * Function to change the gender of the user
	 * @param userId
	 * @param newGender
	 * @return
	 */
	public Profile changeGender(int userId, String newGender) {
		
		Optional<Profile> profile = pr.findByLoginUserId(userId);
		
		profile.get().setGender(newGender);
		
		return pr.save(profile.get());
	}
	
	/**
	 * Function to change the orientation of the user
	 * @param userId
	 * @param newOrientation
	 * @return
	 */
	public Profile changeSexOrientation(int userId, String newOrientation) {
		
		Optional<Profile> profile = pr.findByLoginUserId(userId);
		
		profile.get().setSexOrientation(newOrientation);
		
		return pr.save(profile.get());
	}
}
