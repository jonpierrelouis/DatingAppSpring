package com.dating.services;

import java.util.List;

import com.dating.models.Profile;
import com.dating.repositories.ProfileRepository;
import com.dating.repositories.UserDislikesRepository;
import com.dating.repositories.UserLikesRepository;

public class MatchService {
	
	final private UserLikesRepository ulr;
	final private UserDislikesRepository udr;
	final private ProfileRepository pr;
	
	public MatchService(UserLikesRepository ulr, UserDislikesRepository udr, ProfileRepository pr) {
		super();
		this.ulr = ulr;
		this.udr = udr;
		this.pr = pr;
	}
	
	public List<Profile> findMatches(int userId){
		
		int currentProfileId  = pr.findByLoginUserId(userId).get().getProfileId();
		
		//get list of ints that corresponds to the user's like using the profile id
		
		//custom sql statement using WHERE ... IN and not including the profile id
		
		//choose the people with the most amount of likes and no dislikes
		
		return null;
	}

}
