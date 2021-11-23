package com.bogdan.flightrezervation.repos;

import com.bogdan.flightrezervation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
