package com.dating.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dating.models.Likes;
import com.dating.models.Login;
import com.dating.models.Profile;
import com.dating.models.UserLikes;
import com.dating.repositories.LikeRepository;
import com.dating.repositories.ProfileRepository;
import com.dating.repositories.UserLikesRepository;

@Service
public class ProfileService {
	
	private final ProfileRepository pr;
	private final LikeRepository lr;
	private final UserLikesRepository ulr;

	@Autowired
	public ProfileService(ProfileRepository pr, LikeRepository lr, UserLikesRepository ulr) {
		this.pr = pr;
		this.lr = lr;
		this.ulr = ulr;
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
	
	/**
	 * A private function to handle any likes added a like to its repository
	 * @param like
	 * @return
	 */
	private Likes addLikeToDataTable(String like) {
	
		//set like to lowercase
		StringUtils.capitalize(like);
				
		//save like to the like table
		Likes newLike = new Likes(like);
				
		try {
			return lr.save(newLike);
			
		}catch(DataIntegrityViolationException e) {
			System.out.println("\n This like already exists in the database");
		}
		
		return lr.findBySingleLikeEquals(like);
	}
	
	/**
	 * Function to add a like to their list of likes
	 * @param like
	 * @return
	 */
	public Optional<Profile> addLike(int userId, String like) {
		//add the like to the list of likes
		Likes f = addLikeToDataTable(like);

		int currentProfileId = pr.findByLoginUserId(userId).get().getProfileId();
		
		//create new userlike obj and add the userId and the likeId in the database
		UserLikes newUserLike = new UserLikes(currentProfileId, f.getLikesId());
		
		
		//if it does not exist already save the data
		if(!ulr.existsByUserIdAndLikeId(currentProfileId, userId)) {
			
			ulr.save(newUserLike);
		}
		
		return getProfile(userId);

	}
}
