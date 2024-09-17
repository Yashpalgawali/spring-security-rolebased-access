package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.models.User;

@Repository("userrepo")
public interface UserRepository extends JpaRepository<User, String> {

}
