package com.padel.service;

import com.padel.dto.AuthResponse;
import com.padel.dto.LoginRequest;
import com.padel.dto.RegisterRequest;
import com.padel.entity.Player;
import com.padel.exception.EmailAlreadyExistsException;
import com.padel.repository.PlayerRepository;
import com.padel.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        if (playerRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }
        Player player = new Player(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword())
        );
        playerRepository.save(player);
        String token = jwtUtil.generateToken(player.getEmail(), player.getRole().name());
        return new AuthResponse(token, player.getEmail(), player.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Player player = playerRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Joueur introuvable"));
        String token = jwtUtil.generateToken(player.getEmail(), player.getRole().name());
        return new AuthResponse(token, player.getEmail(), player.getRole().name());
    }
}

