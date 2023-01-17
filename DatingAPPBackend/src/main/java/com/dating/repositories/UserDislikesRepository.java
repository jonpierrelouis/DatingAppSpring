package com.dating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dating.keys.UserLikesKey;
import com.dating.models.UserDislikes;

public interface UserDislikesRepository extends JpaRepository<UserDislikes, UserLikesKey>{
	
	public boolean existsByUserIdAndLikeId(int userId, int likeId);
	
	public void removeByUserIdAndLikeId(int userId, int likeId);

}
