package com.example.portfolio.security.service;

import com.example.portfolio.exceptions.ResourceNotFoundException;
import com.example.portfolio.security.entity.User;
import com.example.portfolio.security.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRep;

    public List<User> getUsers() {
        return userRep.findAll();
    }

    public User findByUserId(Long account_id) {
        return userRep.findById(account_id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", account_id));
    }
    
    public Long findUserIdByUsername(String username) {
        User user = userRep.findUserIdByUsername(username);
        return user.getId();
    }
    
    public Long findPersonIdByUsername(String username) {
        User user = userRep.findPersonIdByUsername(username);
        return user.getPerson().getId();
    }

    public Optional<User> findByUsername(String username) {
        return userRep.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRep.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRep.existsByEmail(email);
    }

    public void createUser(User user) {
        userRep.save(user);
    }

}
