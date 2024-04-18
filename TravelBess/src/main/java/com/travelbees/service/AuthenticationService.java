package com.travelbees.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelbees.entity.AuthenticationResponse;
import com.travelbees.entity.Role;
import com.travelbees.entity.User;
import com.travelbees.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

   // private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
//        this.tokenRepository = tokenRepository;
       this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {

//        // check if user already exist. if exist than authenticate the user
//        if(repository.findByUsername(request.getUsername()).isPresent()) {
//            return new AuthenticationResponse(null, "User already exist");
//        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        user.setRole(request.getRole());

        user = repository.save(user);

      //  String jwt = jwtService.generateToken(user);

      //  saveUserToken(jwt, user);

        return new AuthenticationResponse("hi");

    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        String role = user.getRole()+"";
        //String roleString = role.toString();
        //System.out.println(roleString); // Output: "ADMIN"

return new AuthenticationResponse(jwt,role);
}}