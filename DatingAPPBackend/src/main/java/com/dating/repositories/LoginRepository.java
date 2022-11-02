package com.dating.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dating.models.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {

	Optional<Login> findByUserEmailAndUserPassword(String email, String password);

}
