package com.dhiraj.taxi.booking.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Booking {
    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Drop location is required")
    private String dropLocation;

    @NotBlank(message = "Pickup date is required")
    private String pickupDate;

    @NotBlank(message = "Pickup time is required")
    private String pickupTime;

    @NotBlank(message = "Name is required")
    private String name;
    @Pattern(regexp = "^[0-9]{10}$", message = "Enter a valid 10-digit mobile number")
    @NotBlank(message = "Mobile No is required")
    private String mobileNo;
}