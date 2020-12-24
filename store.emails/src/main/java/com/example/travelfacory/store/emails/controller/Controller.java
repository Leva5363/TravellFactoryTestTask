package com.example.travelfacory.store.emails.controller;

import com.example.travelfacory.store.emails.entity.Campaign;
import com.example.travelfacory.store.emails.service.StoreEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketing/ws/partner/campaign")
public class Controller {

    @Autowired
    StoreEmailService storeEmailService;

    @PostMapping("/save")
    private Campaign creatingCampaign(
        @RequestParam String name, @RequestParam String email, @RequestParam String phone) {
        return storeEmailService.saveCampaign(name, email, phone);
    }

    @GetMapping("/get")
    private Campaign getCampaign(@RequestParam long id) {
        return storeEmailService.getCampaignById(id);
    }

    @DeleteMapping("/delete")
    private ResponseEntity<String> deleteCampaign(@RequestParam long id) {
        storeEmailService.deleteCampaignById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    private Campaign update(@RequestBody Campaign newCampaign, @RequestParam long id) {
        return storeEmailService.updateCampaign(newCampaign, id);
    }

    @GetMapping("/get/all")
    List<Campaign> getAllCampaign() {
        return storeEmailService.getAllCampaign();
    }
}
