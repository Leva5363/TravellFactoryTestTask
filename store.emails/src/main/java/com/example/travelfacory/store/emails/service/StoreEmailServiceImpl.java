package com.example.travelfacory.store.emails.service;

import com.example.travelfacory.store.emails.entity.Campaign;
import com.example.travelfacory.store.emails.exception.NoSuchCampaignException;
import com.example.travelfacory.store.emails.repository.CampaignRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreEmailServiceImpl implements StoreEmailService {
    Logger logger = LoggerFactory.getLogger(StoreEmailServiceImpl.class);

    @Autowired
    CampaignRepository campaignRepository;

    @Override
    public Campaign saveCampaign(String name, String email, String phone) {
        Campaign campaign = Campaign.builder().name(name).email(email).phone(phone).build();
        if (campaignRepository.findByName(name)!=null) {
            logger.info("The Campaign with the same name presents in the DB");
            return campaignRepository.findByName(name);
        }
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign getCampaignById(long id) {
        return campaignRepository.findById(id).orElseThrow(NoSuchCampaignException::new);
    }

    @Override
    public void deleteCampaignById(long id) {
        campaignRepository.deleteById(id);
    }

    @Override
    public Campaign updateCampaign(Campaign newCampaign, long id) {
        return campaignRepository.findById(id)
            .map(campaign -> {
                campaign.setName(newCampaign.getName());
                campaign.setEmail(newCampaign.getEmail());
                campaign.setPhone(newCampaign.getPhone());
                return campaignRepository.save(campaign);
            })
            .orElseGet(() -> {
                newCampaign.setId(id);
                return campaignRepository.save(newCampaign);
            });
    }

    @Override
    public List<Campaign> getAllCampaign() {
        return campaignRepository.findAll();
    }
}
