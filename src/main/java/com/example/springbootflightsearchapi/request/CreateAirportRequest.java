package com.example.springbootflightsearchapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAirportRequest {
    @Size(min = 3, max = 37)
    @NotBlank(message = "Please enter the city name field")
    private String cityName;
}
