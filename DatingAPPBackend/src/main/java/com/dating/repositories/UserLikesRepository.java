package com.dating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dating.keys.UserLikesKey;
import com.dating.models.UserLikes;

public interface UserLikesRepository extends JpaRepository<UserLikes, UserLikesKey> {

	public boolean existsByUserIdAndLikeId(int userId, int likeId);
}
