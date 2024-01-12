package com.example.springbootflightsearchapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFlightRequest {
    private Long departureAirportId;
    private Long arrivalAirportId;
    private BigDecimal price;
    private LocalDate departureDate;
    private LocalDate returnDate;
}
