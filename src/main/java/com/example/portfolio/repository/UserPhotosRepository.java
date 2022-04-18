package com.example.portfolio.repository;

import com.example.portfolio.entity.UserPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotosRepository extends JpaRepository<UserPhotos, Long> {

}
