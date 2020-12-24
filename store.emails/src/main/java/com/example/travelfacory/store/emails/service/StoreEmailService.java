package com.example.travelfacory.store.emails.service;

import com.example.travelfacory.store.emails.entity.Campaign;

import java.util.List;

public interface StoreEmailService {

    Campaign saveCampaign(String name, String email, String phone);

    Campaign getCampaignById(long id);

    void deleteCampaignById(long id);

    Campaign updateCampaign(Campaign campaign, long id);

    List<Campaign> getAllCampaign();
}
