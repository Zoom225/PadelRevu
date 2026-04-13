package com.padel.security;

import com.padel.entity.Player;
import com.padel.repository.PlayerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    public CustomUserDetailsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Player player = playerRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Joueur introuvable: " + email));

        return new User(
            player.getEmail(),
            player.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + player.getRole().name()))
        );
    }
}

