package com.example.travelfacory.store.emails.repository;

import com.example.travelfacory.store.emails.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Campaign findByName(String name);
}
