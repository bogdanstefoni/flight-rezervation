package com.bogdan.flightrezervation.repos;

import com.bogdan.flightrezervation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
