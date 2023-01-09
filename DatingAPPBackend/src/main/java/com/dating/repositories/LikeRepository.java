package com.dating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dating.models.Likes;

public interface LikeRepository extends JpaRepository<Likes, Integer>{

	public Likes findBySingleLikeEquals(String like);
	

}
