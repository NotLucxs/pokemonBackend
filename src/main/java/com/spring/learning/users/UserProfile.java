package com.spring.learning.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @CreatedBy
    @Id
    private String username;

    private String firstName;
    private String lastName;
    private int age;
    @CreatedDate
    private Instant createdDate;


    @Override
    public String toString() {
        return String.format("%s: %s %s, %s, joined %s", username, firstName, lastName, age, createdDate);
    }
}
