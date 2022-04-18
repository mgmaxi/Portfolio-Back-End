package com.example.portfolio.controller;

import com.example.portfolio.entity.SocialNetwork;
import com.example.portfolio.service.SocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SocialNetworkController {

    @Autowired
    private SocialNetworkService socNetServ;

    @GetMapping("/socialnetworks/persons/{person_id}")
    public SocialNetwork findByPersonId(@PathVariable Long person_id) {
        return socNetServ.findByPersonId(person_id);
    }

    @PostMapping("/socialnetworks/persons/{person_id}")
    public ResponseEntity<SocialNetwork> createSocialNetwork(@PathVariable Long person_id, @RequestBody SocialNetwork socialNetwork) {
        return new ResponseEntity<>(socNetServ.createSocialNetwork(person_id, socialNetwork), HttpStatus.CREATED);
    }

    @PutMapping("/socialnetworks/{socialnetwork_id}/persons/{person_id}")
    public ResponseEntity<SocialNetwork> updateSocialNetwork(@PathVariable Long person_id, @PathVariable Long socialnetwork_id, @RequestBody SocialNetwork updatedSocialNetwork) {
        return new ResponseEntity<>(socNetServ.updateSocialNetwork(person_id, socialnetwork_id, updatedSocialNetwork), HttpStatus.OK);
    }

    @DeleteMapping("/socialnetworks/{socialnetwork_id}")
    public ResponseEntity<String> deleteSocialNetwork(@PathVariable Long socialnetwork_id) {
        socNetServ.deleteSocialNetwork(socialnetwork_id);
        return new ResponseEntity<>("The social networks have been deleted.", HttpStatus.OK);
    }

}
