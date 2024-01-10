package com.example.springbootflightsearchapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @OneToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    private double price;
    private Date departureDate;
    private Date arrivalDate;
}
