package org.diplompractice.backendyakhno.service.auth;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.diplompractice.backendyakhno.DTO.LoginUser;
import org.diplompractice.backendyakhno.response.AuthenticationResponse;
import org.diplompractice.backendyakhno.config.JwtUtil;
import org.diplompractice.backendyakhno.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse loginUser(LoginUser request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = JwtUtil.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }



    public String getUsername(String bearerToken){
        String token = bearerToken.substring(7);
        return JwtUtil.parseToken(token).getSubject();
    }

    public boolean isUsernameTaken(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isTokenValid(String bearerToken){
        String token = bearerToken.substring(7);
        try{
            Jwts.parser().verifyWith(JwtUtil.SECRET_KEY).build().parseSignedClaims(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
