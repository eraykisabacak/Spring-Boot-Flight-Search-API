package com.example.springbootflightsearchapi.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightRequest {
    @Size(min = 3, max = 37)
    @NotBlank(message = "Please enter the departure airport field")
    private String departureAirport;

    @Size(min = 3, max = 37)
    @NotBlank(message = "Please enter the arrival airport field")
    private String arrivalAirport;

    @NotBlank(message = "Please enter the price field")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "Departure date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate returnDate;
}
