package org.diplompractice.backendyakhno.controller.auth;

import lombok.RequiredArgsConstructor;

import org.diplompractice.backendyakhno.DTO.LoginUser;
import org.diplompractice.backendyakhno.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) {
        return ResponseEntity.ok(authService.loginUser(loginUser));
    }


    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public String giveUsername(@RequestHeader("Authorization") String bearerToken) {
        return authService.getUsername(bearerToken);
    }

    @RequestMapping(value = "/isUsernameTaken/{username}", method = RequestMethod.GET)
    public boolean isUsernameTaken(@PathVariable String username) {
        return authService.isUsernameTaken(username);
    }

    @RequestMapping(value = "/isTokenValid", method = RequestMethod.GET)
    public boolean isTokenValid(@RequestHeader("Authorization") String bearerToken) {
        return authService.isTokenValid(bearerToken);
    }
}