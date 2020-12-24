package com.example.travelfacory.registration_validation.service;


public interface ValidationService {

    boolean emailValidation(String email);

    boolean phoneValidation(String phone);

}
