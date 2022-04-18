package com.example.portfolio.service;

import com.example.portfolio.entity.UserPhotos;
import com.example.portfolio.exceptions.PortfolioAppException;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.portfolio.repository.UserPhotosRepository;
import com.example.portfolio.security.entity.User;
import com.example.portfolio.security.repository.UserRepository;

@Service
public class UserPhotosService implements IUserPhotosService {

    @Autowired
    private UserPhotosRepository userPhRep;

    @Autowired
    private UserRepository userRep;

    @Override
    public List<UserPhotos> getUserPhotos() {
        return userPhRep.findAll();
    }

    @Override
    public UserPhotos findByUserPhotosId(Long userphotos_id) {
        return userPhRep.findById(userphotos_id).orElseThrow(() -> new ResourceNotFoundException("Account photos", "id", userphotos_id));
    }

    @Override
    public UserPhotos createUserPhotos(Long user_id, UserPhotos userPhotos) {
        User user = userRep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        if (user.getUser_photos() == null) {
            user.setUser_photos(userPhotos);
            return userPhRep.save(userPhotos);
        } else {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The user " + user.getUsername() + " already has associated photos.");
        }
    }

    @Override
    public UserPhotos updateUserPhotos(Long user_id, Long userphotos_id, UserPhotos updatedUserPhotos) {
        User user = userRep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        UserPhotos userPhotos = userPhRep.findById(userphotos_id).orElseThrow(() -> new ResourceNotFoundException("Account photos", "id", userphotos_id));
        if ((user.getUser_photos() == null) || !(user.getUser_photos().equals(userPhotos))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The photos with the id " + userPhotos.getId() + " doesn't belong to the user " + user.getUsername() + ".");
        } else {
            updatedUserPhotos.setId(userphotos_id);
            return userPhRep.save(updatedUserPhotos);
        }
    }
    
    @Override
    public UserPhotos updateUserProfilePhoto(Long user_id, Long userphotos_id, UserPhotos updatedUserPhotos) {
        User user = userRep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        UserPhotos userPhotos = userPhRep.findById(userphotos_id).orElseThrow(() -> new ResourceNotFoundException("Account photos", "id", userphotos_id));
        if ((user.getUser_photos() == null) || !(user.getUser_photos().equals(userPhotos))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The photos with the id " + userPhotos.getId() + " doesn't belong to the user " + user.getUsername() + ".");
        } else {
            updatedUserPhotos.setId(userphotos_id);
            updatedUserPhotos.setCover_photo(userPhotos.getCover_photo());
            return userPhRep.save(updatedUserPhotos);
        }
    }
    
    @Override
    public UserPhotos updateUserCoverPhoto(Long user_id, Long userphotos_id, UserPhotos updatedUserPhotos) {
        User user = userRep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        UserPhotos userPhotos = userPhRep.findById(userphotos_id).orElseThrow(() -> new ResourceNotFoundException("Account photos", "id", userphotos_id));
        if ((user.getUser_photos() == null) || !(user.getUser_photos().equals(userPhotos))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The photos with the id " + userPhotos.getId() + " doesn't belong to the user " + user.getUsername() + ".");
        } else {
            updatedUserPhotos.setId(userphotos_id);
            updatedUserPhotos.setProfile_photo(userPhotos.getProfile_photo());
            return userPhRep.save(updatedUserPhotos);
        }
    }
    

    @Override
    public void deleteUserPhotos(Long user_id, Long userphotos_id) {
        User user = userRep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        UserPhotos userPhotos = userPhRep.findById(userphotos_id).orElseThrow(() -> new ResourceNotFoundException("Account photos", "id", userphotos_id));
        if ((user.getUser_photos() == null) || !(user.getUser_photos().equals(userPhotos))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The photos with the id " + userPhotos.getId() + " doesn't belong to the user " + user.getUsername() + ".");
        } else {
            user.setUser_photos(null);
            userPhRep.deleteById(userphotos_id);
        }
    }
}
