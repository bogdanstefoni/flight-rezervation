package com.bogdan.flightrezervation.services;

import com.bogdan.flightrezervation.dto.ReservationRequest;
import com.bogdan.flightrezervation.entities.Reservation;

public interface ReservationService {

    public Reservation bookFlight(ReservationRequest request);

}
