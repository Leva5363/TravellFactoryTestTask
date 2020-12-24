package com.example.travelfacory.registration_validation.controller;

import com.example.travelfacory.registration_validation.models.AuthenticationRequest;
import com.example.travelfacory.registration_validation.models.AuthenticationResponse;
import com.example.travelfacory.registration_validation.response_entity.ResponseEntityCampaign;
import com.example.travelfacory.registration_validation.security.JwtUtil;
import com.example.travelfacory.registration_validation.security.MyUserDetailsService;
import com.example.travelfacory.registration_validation.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/marketing/ws/partner/campaign")
public class Controller {

    @Autowired
    ValidationService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @PostMapping("/save")
    ResponseEntity<String> creating(@RequestParam String name, @RequestParam String email, @RequestParam String phone) {
        Logger logger = LoggerFactory.getLogger(Controller.class);
        if (!service.emailValidation(email)) {
            return new ResponseEntity<>( "wrong format for field: email",HttpStatus.BAD_REQUEST);
        }if (!service.phoneValidation(phone)) {
            return new ResponseEntity<>("wrong format for field: phone", HttpStatus.BAD_REQUEST);
        }
        String url = "http://localhost:8080/marketing/ws/partner/campaign/save";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("name", name)
            .queryParam("email", email).queryParam("phone", phone).build().encode().toUri();
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(new RequestEntity<>(HttpMethod.POST,uri), String.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The Campaign can't be saved", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    ResponseEntity<ResponseEntityCampaign> getCampaigns(@RequestParam long id) {
        String url = "http://localhost:8080/marketing/ws/partner/campaign/get";
        URI uriBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParam("id", id).build().encode().toUri();
        return new RestTemplate().exchange(new RequestEntity<>(HttpMethod.GET, uriBuilder), ResponseEntityCampaign.class);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username or Password",e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/test")
        public String test(){
        return "Succeeds";
        }
}
