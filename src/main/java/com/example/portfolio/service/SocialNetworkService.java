package com.example.portfolio.service;

import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.SocialNetwork;
import com.example.portfolio.exceptions.PortfolioAppException;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import com.example.portfolio.repository.SocialNetworkRepository;
import com.example.portfolio.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SocialNetworkService implements ISocialNetworkService {

    @Autowired
    private SocialNetworkRepository socNetRep;

    @Autowired
    private PersonRepository perRep;

    @Override
    public SocialNetwork findByPersonId(Long person_id) {
        perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        return socNetRep.findByPersonId(person_id);
    }

    @Override
    public SocialNetwork createSocialNetwork(Long person_id, SocialNetwork socialNetwork) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        if (person.getSocial_networks() == null) {
            person.setSocial_networks(socialNetwork);
            return socNetRep.save(socialNetwork);
        } else {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name() + " already has associated social networks.");
        }
    }

    @Override
    public SocialNetwork updateSocialNetwork(Long person_id, Long socialNetwork_id, SocialNetwork updatedSocialNetwork) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        SocialNetwork socialNetwork = socNetRep.findById(socialNetwork_id).orElseThrow(() -> new ResourceNotFoundException("Social network", "id", socialNetwork_id));
        if ((person.getSocial_networks() == null) || !(person.getSocial_networks().equals(socialNetwork))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "Social networks with the id " + socialNetwork.getId() + " doesn't belong to " + person.getFirst_name() + ".");
        } else {
            updatedSocialNetwork.setId(socialNetwork_id);
            updatedSocialNetwork.setPerson(person);
            return socNetRep.save(updatedSocialNetwork);
        }
    }

    @Override
    public void deleteSocialNetwork(Long socialNetwork_id) {
        socNetRep.findById(socialNetwork_id).orElseThrow(() -> new ResourceNotFoundException("Social network", "id", socialNetwork_id));
        socNetRep.deleteById(socialNetwork_id);
    }
}
