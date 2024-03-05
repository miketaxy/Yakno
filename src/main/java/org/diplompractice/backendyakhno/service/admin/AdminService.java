package org.diplompractice.backendyakhno.service.admin;

import lombok.RequiredArgsConstructor;
import org.diplompractice.backendyakhno.DTO.LoginUser;
import org.diplompractice.backendyakhno.config.JwtUtil;
import org.diplompractice.backendyakhno.repository.UserRepository;
import org.diplompractice.backendyakhno.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<AuthenticationResponse> registerUser(LoginUser request) {//TODO: remake this method
        String passwordEncode = passwordEncoder.encode(request.getPassword());
        try {
            var user = userRepository.save(request.toUser(passwordEncode));
            var jwtToken = JwtUtil.generateToken(user);
            return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).message("success").build());
        } catch (Exception e) {
            return ResponseEntity.status(409).body(AuthenticationResponse.builder().token(null).message("Username is taken").build());
        }
    }
}
