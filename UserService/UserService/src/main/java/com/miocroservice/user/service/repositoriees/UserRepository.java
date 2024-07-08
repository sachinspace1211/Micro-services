package com.miocroservice.user.service.repositoriees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miocroservice.user.service.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
