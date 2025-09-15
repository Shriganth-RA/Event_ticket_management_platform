package com.em.event_management.filters;

import java.io.IOException;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.em.event_management.domain.entities.User;
import com.em.event_management.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserProvisioningFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt jwt) {

                    UUID keycloakID = UUID.fromString(jwt.getSubject());
                    
                    if (!userRepository.existsById(keycloakID)) {

                        User user = new User();
                        user.setId(keycloakID);
                        user.setName(jwt.getClaimAsString("preferred_username"));
                        user.setEmail(jwt.getClaimAsString("email"));

                        userRepository.save(user);

                    }
                }

                filterChain.doFilter(request, response);
    }
    
}
