package org.diplompractice.backendyakhno.controller.auth;


import lombok.RequiredArgsConstructor;
import org.diplompractice.backendyakhno.DTO.LoginUser;
import org.diplompractice.backendyakhno.model.User;
import org.diplompractice.backendyakhno.response.AuthenticationResponse;
import org.diplompractice.backendyakhno.service.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)//TODO: remake for admin
    public ResponseEntity<AuthenticationResponse> register(@RequestBody LoginUser registerUser) {
        return adminService.registerUser(registerUser);
    }



}
