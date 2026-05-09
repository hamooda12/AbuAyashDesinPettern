package com.example.hotalproject.security.auth;

import com.example.hotalproject.HotelCatalog.Utility.Exceptions.ConflictException;
import com.example.hotalproject.security.AppUser;
import com.example.hotalproject.security.AppUserRepository;
import com.example.hotalproject.security.JwtService;
import com.example.hotalproject.security.Role;
import com.example.hotalproject.security.refresh.RefreshToken;
import com.example.hotalproject.security.refresh.RefreshTokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AppUserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private RefreshTokenService refreshTokenService;

    @InjectMocks
    private AuthService authService;


    @Test
    void register_shouldThrowConflictWhenUserExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("guest@test.com");
        request.setPassword("password");

        when(userRepository.existsByEmail("guest@test.com")).thenReturn(true);

        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("User already exists");
    }

    @Test
    void login_shouldThrowUnauthorizedWhenCredentialsInvalid() {
        LoginRequest request = new LoginRequest();
        request.setEmail("wrong@test.com");
        request.setPassword("bad");

        doThrow(new BadCredentialsException("bad")).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessageContaining("Invalid email or password");
    }

    @Test
    void refresh_shouldRotateAndReturnNewTokens() {
        AppUser user = AppUser.builder().id(5L).email("guest@test.com").role(Role.GUEST).build();
        RefreshToken rotated = RefreshToken.builder().token("rotated-refresh").user(user).build();
        RefreshTokenRequest request = new RefreshTokenRequest();
        request.setRefreshToken("old-refresh");

        when(refreshTokenService.verifyAndRotate("old-refresh")).thenReturn(rotated);
        when(jwtService.generateToken(user)).thenReturn("new-access");
        when(jwtService.getAccessTokenExpirationMs()).thenReturn(900000L);

        refrechResponse response = authService.refresh(request);

        assertThat(response.getAccessToken()).isEqualTo("new-access");

        assertThat(response.getEmail()).isEqualTo("guest@test.com");
    }
}
