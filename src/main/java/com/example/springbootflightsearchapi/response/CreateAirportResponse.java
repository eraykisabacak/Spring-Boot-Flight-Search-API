package com.example.springbootflightsearchapi.response;

import com.example.springbootflightsearchapi.model.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAirportResponse {
    private Airport airport;
}
