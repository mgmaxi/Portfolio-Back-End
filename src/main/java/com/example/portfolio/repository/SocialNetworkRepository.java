package com.example.portfolio.repository;

import com.example.portfolio.entity.SocialNetwork;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long> {

    public SocialNetwork findByPersonId(Long person_id);

}
