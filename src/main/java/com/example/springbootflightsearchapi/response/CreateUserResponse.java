package com.example.springbootflightsearchapi.response;

import com.example.springbootflightsearchapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private String message;
    private User user;
}
