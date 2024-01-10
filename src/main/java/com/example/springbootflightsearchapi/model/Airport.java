package com.example.springbootflightsearchapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;

    @OneToOne(mappedBy = "departureAirport")
    @JsonIgnore
    private Flight departingFlight;

    @OneToOne(mappedBy = "arrivalAirport")
    @JsonIgnore
    private Flight arrivingFlight;
}
