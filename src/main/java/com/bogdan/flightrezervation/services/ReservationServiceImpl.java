package com.bogdan.flightrezervation.services;

import com.bogdan.flightrezervation.dto.ReservationRequest;
import com.bogdan.flightrezervation.entities.Flight;
import com.bogdan.flightrezervation.entities.Passenger;
import com.bogdan.flightrezervation.entities.Reservation;
import com.bogdan.flightrezervation.repos.FlightRepository;
import com.bogdan.flightrezervation.repos.PassengerRepository;
import com.bogdan.flightrezervation.repos.ReservationRepository;
import com.bogdan.flightrezervation.util.EmailUtil;
import com.bogdan.flightrezervation.util.PDFGenerator;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Value("${com.bogdan.flight-rezervation.itinerary.dirpath}")
    public String ITINERARY_DIR;
    @Autowired
    PDFGenerator pdfGenerator;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Reservation bookFlight(ReservationRequest request) {

        //Make payment

        Long flightId = request.getFlightId();
        Flight flight = flightRepository.findById(flightId).orElseThrow();
        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setPhone(request.getPassengerPhone());
        passenger.setEmail(request.getPassengerEmail());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        Reservation savedReservation = reservationRepository.save(reservation);

        try {
            String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
            pdfGenerator.generateItinerary(savedReservation,
                    filePath);
            emailUtil.sendItinerary(passenger.getEmail(), filePath);
        } catch (FileNotFoundException | MessagingException | DocumentException e) {
            e.printStackTrace();
        }

        return savedReservation;

    }
}
