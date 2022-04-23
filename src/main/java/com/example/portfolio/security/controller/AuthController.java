package com.example.portfolio.security.controller;

import com.example.portfolio.dto.MessageSent;
import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.SocialNetwork;
import com.example.portfolio.entity.UserPhotos;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.repository.SocialNetworkRepository;
import com.example.portfolio.repository.UserPhotosRepository;
import com.example.portfolio.security.dto.JwtDto;
import com.example.portfolio.security.dto.NewUser;
import com.example.portfolio.security.dto.UserLogin;
import com.example.portfolio.security.entity.Role;
import com.example.portfolio.security.entity.User;
import com.example.portfolio.security.enums.RoleName;
import com.example.portfolio.security.jwt.JwtProvider;
import com.example.portfolio.security.service.RoleService;
import com.example.portfolio.security.service.UserService;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserPhotosRepository userPhRep;
    
    @Autowired
    private SocialNetworkRepository socNetRep;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<User> getAccounts() {
        return userService.getUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{user_id}")
    public ResponseEntity<User> findByUserId(@PathVariable Long user_id) {
        return new ResponseEntity<>(userService.findByUserId(user_id), HttpStatus.OK);
    }
    
    @GetMapping("/users/{username}/userid")
    public ResponseEntity<Long> findUserIdByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findUserIdByUsername(username), HttpStatus.OK);
    }
    
    @GetMapping("/users/{username}/persons")
    public ResponseEntity<Long> findPersonIdByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findPersonIdByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new MessageSent("The data is incorrect."), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUsername(newUser.getUsername())) {
            return new ResponseEntity(new MessageSent("Username is not available. Try another."), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity(new MessageSent("Email is not available. Try another."), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUser.getUsername(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName(RoleName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin")) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN).get());
        }
        // ADD ROLE_ADMIN TO ALL USERS THAT SIGN UP
        roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);

        // Create person and link it to user
        Person person = new Person();
        person.setFirst_name(newUser.getFirst_name());
        person.setLast_name(newUser.getLast_name());
        user.setPerson(person);
        personRepository.save(person);

        // Create socialnetworks and link it to person
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.setEmail(user.getEmail());
        socialNetwork.setPerson(person);
        socNetRep.save(socialNetwork);

        // Create userphotos and link it to user
        UserPhotos user_photos = new UserPhotos();
        user.setUser_photos(user_photos);
        userPhRep.save(user_photos);

        userService.createUser(user);
        return new ResponseEntity<>(new MessageSent("Successfully registered user."), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new MessageSent("Incorrect data."), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException, java.text.ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

}
