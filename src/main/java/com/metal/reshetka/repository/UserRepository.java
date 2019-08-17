package com.metal.reshetka.repository;

import com.metal.reshetka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

//    List<User> findAll();
}
