package com.carrental.controller;

import com.carrental.model.Vehicle;
import com.carrental.repository.VehicleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/vehicles")
public class AdminVehicleController {

    private final VehicleRepository vehicleRepository;

    public AdminVehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public String adminVehicles(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");

        if (!"ADMIN".equals(role)) {
            return "redirect:/login";
        }

        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "admin-vehicles";
    }

    @PostMapping("/add")
    public String addVehicle(
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam int year,
            @RequestParam double dailyRate,
            HttpSession session) {

        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }
 
        vehicleRepository.save(new Vehicle(make, model, year, dailyRate));
        return "redirect:/admin/vehicles";
    }

    @PostMapping("/delete")
    public String deleteVehicle(@RequestParam Long id, HttpSession session) {

        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        vehicleRepository.deleteById(id);
        return "redirect:/admin/vehicles";
    }
}
