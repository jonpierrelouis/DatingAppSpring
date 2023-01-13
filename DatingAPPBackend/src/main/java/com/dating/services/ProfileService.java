package com.dating.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dating.models.Likes;
import com.dating.models.Login;
import com.dating.models.Profile;
import com.dating.models.UserDislikes;
import com.dating.models.UserLikes;
import com.dating.repositories.LikeRepository;
import com.dating.repositories.ProfileRepository;
import com.dating.repositories.UserDislikesRepository;
import com.dating.repositories.UserLikesRepository;

@Service
public class ProfileService {
	
	private final ProfileRepository pr;
	private final LikeRepository lr;
	private final UserLikesRepository ulr;
	private final UserDislikesRepository udr;

	@Autowired
	public ProfileService(ProfileRepository pr, LikeRepository lr, UserLikesRepository ulr, UserDislikesRepository udr) {
		this.pr = pr;
		this.lr = lr;
		this.ulr = ulr;
		this.udr = udr;
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
	 * Function to add a like to their list of likes and return the updated profile
	 * @param like
	 * @return
	 */
	public Profile addLike(int userId, String like) {
		//add the like to the list of likes
		Likes f = addLikeToDataTable(like);

		int currentProfileId = pr.findByLoginUserId(userId).get().getProfileId();
		
		//create new userlike obj and add the userId and the likeId in the database
		UserLikes newUserLike = new UserLikes(currentProfileId, f.getLikesId());
		
		
		//if it does not exist already save the data
		if(!ulr.existsByUserIdAndLikeId(currentProfileId, userId)) {
			
			ulr.save(newUserLike);
		}
		
		return getProfile(userId).get();

	}
	
	/**
	 * Function to add a dislike to the list of their dislikes and return the updated profile
	 * @param userId
	 * @param dislike
	 * @return
	 */
	public Profile addDislike(int userId, String dislike) {
	
		//add like to the list
		Likes f = addLikeToDataTable(dislike);
		
		int currentProfileId = pr.findByLoginUserId(userId).get().getProfileId();
		
		UserDislikes newUserLike = new UserDislikes(currentProfileId, f.getLikesId());
		
		//if it does not exist already save the data
		if(!udr.existsByUserIdAndLikeId(currentProfileId, userId)) {
					
			udr.save(newUserLike);
		}
		
		return getProfile(userId).get();
	}
	
	/**
	 * This function will remove a like from the user's like list  and return the updated profile
	 * @param userId
	 * @param like
	 * @return
	 */
	@Transactional
	public Profile removeLike(int userId, String like) {
		
		int currentProfileId  = pr.findByLoginUserId(userId).get().getProfileId();
		
		int likeId = lr.findBySingleLikeEquals(like).getLikesId();
		
		ulr.removeByUserIdAndLikeId(currentProfileId, likeId);
		
		return getProfile(userId).get();
	}
	
	/**
	 * This function will remove a dislike from the user's like list  and return the updated profile
	 * @param userId
	 * @param like
	 * @return
	 */
	@Transactional
	public Profile removeDislike(int userId, String like) {
		
		int currentProfileId  = pr.findByLoginUserId(userId).get().getProfileId();
		
		int likeId = lr.findBySingleLikeEquals(like).getLikesId();
		
		udr.removeByUserIdAndLikeId(currentProfileId, likeId);
		
		return getProfile(userId).get();
	}
}
