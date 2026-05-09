package com.example.hotalproject.security.auth;

import com.example.hotalproject.HotelCatalog.Utility.Exceptions.ConflictException;
import com.example.hotalproject.security.AppUser;
import com.example.hotalproject.security.AppUserRepository;
import com.example.hotalproject.security.JwtService;
import com.example.hotalproject.security.Role;
import com.example.hotalproject.security.refresh.RefreshToken;
import com.example.hotalproject.security.refresh.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("User already exists with email: " + request.getEmail());
        }
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new ConflictException("User already exists with userName: " + request.getEmail());
        }


        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(request.getPassword()).userName(request.getUserName())
                .role(request.getRole() == null ? Role.GUEST : request.getRole())
                .build();

        AppUser saved = userRepository.save(user);
refreshTokenService.revokeAllUserTokens(saved);
        RefreshToken refreshToken = refreshTokenService.issueToken(saved);

        return buildAuthResponse(saved, refreshToken.getToken());
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid email or password");
        }

        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        refreshTokenService.revokeAllUserTokens(user);
        RefreshToken refreshToken = refreshTokenService.issueToken(user);

        return buildAuthResponse(user, refreshToken.getToken());
    }

    @Transactional
    public refrechResponse refresh(RefreshTokenRequest request) {
        RefreshToken rotatedToken = refreshTokenService.verifyAndRotate(request.getRefreshToken());

        return refrechResponse.builder()
                .accessToken(jwtService.generateToken(rotatedToken.getUser()))
                .userName(rotatedToken.getUser().getUsername())
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenExpirationMs())
                .email(rotatedToken.getUser().getEmail())
                .role(rotatedToken.getUser().getRole())

                .build();
    }

    @Transactional
    public void logout(LogoutRequest request) {
        refreshTokenService.revokeByToken(request.getRefreshToken());
    }

    private AuthResponse buildAuthResponse(AppUser user, String refreshToken) {
        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(user)) // ✅ FIX
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenExpirationMs()) // ✅ FIX
                .email(user.getEmail())
                .role(user.getRole())
                .userName(user.getUsername())
                .build();
    }
}