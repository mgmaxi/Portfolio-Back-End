package com.example.portfolio.service;

import com.example.portfolio.entity.UserPhotos;
import java.util.List;

public interface IUserPhotosService {

    public List<UserPhotos> getUserPhotos();

    public UserPhotos findByUserPhotosId(Long userPhotos_id);

    public UserPhotos createUserPhotos(Long user_id, UserPhotos userPhotos);

    public UserPhotos updateUserPhotos(Long user_id, Long userPhotos_id, UserPhotos userPhotos);
    
    public UserPhotos updateUserProfilePhoto(Long user_id, Long userPhotos_id, UserPhotos userPhotos);
    
    public UserPhotos updateUserCoverPhoto(Long user_id, Long userPhotos_id, UserPhotos userPhotos);

    public void deleteUserPhotos(Long user_id, Long userPhotos_id);

}