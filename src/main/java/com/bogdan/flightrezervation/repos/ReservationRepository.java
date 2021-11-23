package com.bogdan.flightrezervation.repos;

import com.bogdan.flightrezervation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
