package com.dating.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dating.models.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

	Optional<Login> findByUserEmailAndUserPassword(String email, String password);
	
	Optional<Login> findByUserId(int userId);

}
