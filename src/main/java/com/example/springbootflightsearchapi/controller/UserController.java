package com.example.springbootflightsearchapi.controller;

import com.example.springbootflightsearchapi.request.AuthRequest;
import com.example.springbootflightsearchapi.request.CreateUserRequest;
import com.example.springbootflightsearchapi.response.CreateUserResponse;
import com.example.springbootflightsearchapi.response.LoginResponse;
import com.example.springbootflightsearchapi.service.AuthenticationService;
import com.example.springbootflightsearchapi.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> addNewUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        log.info("Add New User {} " + createUserRequest.getUsername());
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> generateToken(@Valid @RequestBody AuthRequest authRequest) {
        log.info("Login, username {} " + authRequest.getUsername());
        return ResponseEntity.ok(authenticationService.login(authRequest));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
