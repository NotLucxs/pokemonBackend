package com.spring.learning.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserProfile, String> {

    List<UserProfile> findUserByLastName(String lastName);

    UserProfile findByUsername(String username);
}
