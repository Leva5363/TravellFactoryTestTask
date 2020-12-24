package com.example.travelfacory.registration_validation.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationServiceImpl implements ValidationService {

    final static String EMAIL_REGEX = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    final static String PHONE_REGEX = "^\\+?(972|0)(\\-)?0?(([23489]{1}\\d{7})|[5]{1}\\d{8})$";

    @Override
    public boolean emailValidation(String email) {
        if (email == null) {
            return false;
        }
        Pattern p = Pattern.compile(EMAIL_REGEX);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public boolean phoneValidation(String phone) {
        if (phone == null) {
            return false;
        }
        Pattern p = Pattern.compile(PHONE_REGEX);
        Matcher m = p.matcher(phone);
        return m.matches();

    }
}
