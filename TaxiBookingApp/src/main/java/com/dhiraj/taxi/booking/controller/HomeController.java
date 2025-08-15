package com.dhiraj.taxi.booking.controller;

import com.dhiraj.taxi.booking.entity.Booking;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("booking", new Booking());
        return "index";
    }

    @PostMapping("/book")
    public String submitBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        model.addAttribute("booking", booking);
        return "success";
    }
}
