package com.sprindao.jwt.controller;

import com.sprindao.jwt.dto.AuthResponse;
import com.sprindao.jwt.dto.LoginRequest;
import com.sprindao.jwt.dto.RefreshRequest;
import com.sprindao.jwt.entity.RefreshToken;
import com.sprindao.jwt.exception.BusinessException;
import com.sprindao.jwt.security.JwtUtil;
import com.sprindao.jwt.service.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RefreshTokenService refreshTokenService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        refreshTokenService.deleteByUsername(loginRequest.getUsername());
        refreshTokenService.createRefreshToken(loginRequest.getUsername(), refreshToken);

        return new AuthResponse(accessToken, refreshToken);

    }

    @PostMapping("/auth/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest refreshRequest) {
        RefreshToken storedToken = refreshTokenService.findByToken(refreshRequest.refreshToken())
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new BusinessException("Invalid refresh token"));

        String username = storedToken.getUsername();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String newAccessToken = jwtUtil.generateAccessToken(userDetails);
        String newRefreshToken = jwtUtil.generateRefreshToken(userDetails);

        refreshTokenService.deleteByUsername(username);
        refreshTokenService.createRefreshToken(username, newRefreshToken);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }

    @PostMapping("/auth/logout")
    public String logout(@RequestParam String username) {
        refreshTokenService.deleteByUsername(username);
        return "User logged out successfully!!";
    }
}
