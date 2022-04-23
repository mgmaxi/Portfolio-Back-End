package com.example.portfolio.controller;

import com.example.portfolio.entity.UserPhotos;
import com.example.portfolio.service.UserPhotosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api")
public class UserPhotosController {

    @Autowired
    private UserPhotosService userPhServ;

    @GetMapping("/userphotos")
    public List<UserPhotos> getUserPhotos() {
        return userPhServ.getUserPhotos();
    }

    @GetMapping("/userphotos/{userphotos_id}")
    public ResponseEntity<UserPhotos> findByUserPhotosId(@PathVariable Long userphotos_id) {
        return new ResponseEntity<>(userPhServ.findByUserPhotosId(userphotos_id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/userphotos/users/{user_id}")
    public ResponseEntity<UserPhotos> createUserPhotos(@PathVariable Long account_id, @RequestBody UserPhotos userPhotos) {
        return new ResponseEntity<>(userPhServ.createUserPhotos(account_id, userPhotos), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/userphotos/{userphotos_id}/users/{user_id}")
    public ResponseEntity<UserPhotos> updateUserPhotos(@PathVariable Long user_id, @PathVariable Long userphotos_id, @RequestBody UserPhotos userPhotos) {
        return new ResponseEntity<>(userPhServ.updateUserPhotos(user_id, userphotos_id, userPhotos), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/userphotos/{userphotos_id}/profilephoto/users/{user_id}")
    public ResponseEntity<UserPhotos> updateUserProfilePhoto(@PathVariable Long user_id, @PathVariable Long userphotos_id, @RequestBody UserPhotos userPhotos) {
        return new ResponseEntity<>(userPhServ.updateUserProfilePhoto(user_id, userphotos_id, userPhotos), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/userphotos/{userphotos_id}/coverphoto/users/{user_id}")
    public ResponseEntity<UserPhotos> updateUserCoverPhoto(@PathVariable Long user_id, @PathVariable Long userphotos_id, @RequestBody UserPhotos userPhotos) {
        return new ResponseEntity<>(userPhServ.updateUserCoverPhoto(user_id, userphotos_id, userPhotos), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/userphotos/{userphotos_id}/users/{user_id}")
    public ResponseEntity<String> deleteUserPhotos(@PathVariable Long user_id, @PathVariable Long userphotos_id) {
        userPhServ.deleteUserPhotos(user_id, userphotos_id);
        return new ResponseEntity<>("The account photos have been deleted.", HttpStatus.OK);
    }
}
