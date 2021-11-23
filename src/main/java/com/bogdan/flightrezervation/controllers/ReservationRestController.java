package com.bogdan.flightrezervation.controllers;

import com.bogdan.flightrezervation.dto.ReservationUpdateRequest;
import com.bogdan.flightrezervation.entities.Reservation;
import com.bogdan.flightrezervation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id) {

        return reservationRepository.findById(id).orElseThrow();
    }

    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
        Reservation reservation = reservationRepository.findById(request.getId()).orElseThrow();
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());

        return reservationRepository.save(reservation);
    }
}
