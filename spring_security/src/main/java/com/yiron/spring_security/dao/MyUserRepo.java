package com.yiron.spring_security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yiron.spring_security.model.MyUser;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser, Integer> {

	public Optional<MyUser> findByUserName( String userName);

}
