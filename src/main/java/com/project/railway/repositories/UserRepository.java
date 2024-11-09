package com.project.railway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.railway.entities.User;

@Repository
public interface UserRepository {
	User findByEmail(String email);
	public User save(User u);
}