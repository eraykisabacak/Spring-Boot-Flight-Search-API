package com.example.springbootflightsearchapi.response;

import com.example.springbootflightsearchapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private User user;
}
