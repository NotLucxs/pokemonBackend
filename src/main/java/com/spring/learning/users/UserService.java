package com.spring.learning.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserProfile userProfile) {
        userRepository.save(userProfile);
    }

    public void deleteUser(UserProfile userProfile) {
        userRepository.delete(userProfile);
    }

    public UserProfile getUserProfile(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserProfile> getAllUserProfiles(){
        var iterator = userRepository.findAll().iterator();
        var list = new ArrayList<UserProfile>();
        iterator.forEachRemaining(list::add);
        return list;
    }

    public void generateUsers() {
        var user1 = UserProfile.builder().username("NotLucas").firstName("Luke").lastName("McMahon").age(24).createdDate(Instant.now()).build();
        var user2 = UserProfile.builder().username("MazBean").firstName("Mariam").lastName("Ogundimu").age(24).createdDate(Instant.now()).build();
        var user3 = UserProfile.builder().username("JoeMama").firstName("Joe").lastName("Bloggs").age(37).createdDate(Instant.now()).build();
        createUser(user1);
        createUser(user2);
        createUser(user3);
    }
}
