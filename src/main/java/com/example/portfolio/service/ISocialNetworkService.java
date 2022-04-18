package com.example.portfolio.service;

import com.example.portfolio.entity.SocialNetwork;
import java.util.List;


public interface ISocialNetworkService {
    
    public SocialNetwork findByPersonId(Long person_id);

    public SocialNetwork createSocialNetwork(Long person_id, SocialNetwork socialNetwork);

    public SocialNetwork updateSocialNetwork(Long person_id, Long socialNetwork_id, SocialNetwork socialNetwork);

    public void deleteSocialNetwork(  Long socialNetwork_id);
    
}
