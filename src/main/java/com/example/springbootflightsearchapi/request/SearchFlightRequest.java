package com.example.springbootflightsearchapi.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlightRequest {
    @NotNull(message = "Please enter the departure airport field")
    private Long departureAirportId;

    @NotNull(message = "Please enter the arrival airport field")
    private Long arrivalAirportId;

    @NotNull(message = "Departure date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate returnDate;
}
