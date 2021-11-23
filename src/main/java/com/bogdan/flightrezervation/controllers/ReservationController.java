package com.bogdan.flightrezervation.controllers;

import com.bogdan.flightrezervation.dto.ReservationRequest;
import com.bogdan.flightrezervation.entities.Flight;
import com.bogdan.flightrezervation.entities.Reservation;
import com.bogdan.flightrezervation.repos.FlightRepository;
import com.bogdan.flightrezervation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ReservationController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId,
                                          ModelMap modelMap) {
        Flight flight = flightRepository.findById(flightId).orElseThrow();

        modelMap.addAttribute("flight", flight);

        return "completeReservation";
    }

    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest request, ModelMap modelMap ) {

        Reservation reservation = reservationService.bookFlight(request);

        modelMap.addAttribute("msg", "Reservation created successfully with the id: "
                + reservation.getId());

        return "reservationConfirmation";
    }
}
