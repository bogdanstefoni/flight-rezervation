package com.bogdan.flightrezervation.repos;

import com.bogdan.flightrezervation.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
