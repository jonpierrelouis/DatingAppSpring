package com.dating.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dating.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	public Optional<Profile> findByLoginUserId(int userId);
	
}
