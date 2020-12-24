package com.example.travelfacory.registration_validation.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private  final String jwt;
}
