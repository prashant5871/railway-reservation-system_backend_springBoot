package com.project.railway.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.railway.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private EntityManager entityManager;

	@Autowired
	public UserRepositoryImpl(EntityManager e) {
		this.entityManager = e;
	}

	@Override
	public User findByEmail(String email) {
		List<User> results = entityManager.createQuery("from User where email=:e", User.class)
                .setParameter("e", email)
                .getResultList();
return results.isEmpty() ? null : results.get(0);

	}

	@Override
	@Transactional
	public User save(User u) {
		u.setPassword("{noop}" + u.getPassword());
		User uu = entityManager.merge(u);

		return uu;
	}

}
