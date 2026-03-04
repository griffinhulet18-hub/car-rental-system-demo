package com.carrental.controller;

import com.carrental.model.Reservation;
import com.carrental.repository.ReservationRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservations/new")
    public String newReservation(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "reservation-form";
    }

    @PostMapping("/reservations")
    public String createReservation(
            @RequestParam String vehicle,
            @RequestParam String startDate,
            @RequestParam String endDate,
            HttpSession session) {

        String username = (String) session.getAttribute("username");
        Reservation reservation = new Reservation(username, vehicle, startDate, endDate);

        reservationRepository.save(reservation);

        return "redirect:/reservations/my";
    }

    @GetMapping("/reservations/my")
    public String myReservations(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null) {
            return "redirect:/login";
        }

        if ("ADMIN".equals(role)) {
            model.addAttribute("reservations", reservationRepository.findAll());
        } else {
            model.addAttribute("reservations", reservationRepository.findByUsername(username));
        }

        return "my-reservations";
    }
}
