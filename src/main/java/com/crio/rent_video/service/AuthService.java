package com.crio.rent_video.service;

import com.crio.rent_video.controller.exchanges.AuthRequest;
import com.crio.rent_video.controller.exchanges.AuthResponse;
import com.crio.rent_video.controller.exchanges.RegisterRequest;
import com.crio.rent_video.entity.User;
import com.crio.rent_video.enums.Role;
import com.crio.rent_video.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request){
        if(request.getRole()==null){
            request.setRole(Role.CUSTOMER);
        }

        User user= User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        return AuthResponse.builder().build();
    }

    public AuthResponse login(AuthRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        return AuthResponse.builder().build();
    }

}
