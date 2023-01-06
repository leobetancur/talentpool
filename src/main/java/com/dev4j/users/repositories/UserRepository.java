package com.dev4j.users.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev4j.users.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

	public List<UserEntity> findByNickNameStartingWith(String startWith);
}
