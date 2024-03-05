package org.diplompractice.backendyakhno.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.diplompractice.backendyakhno.model.User;
import org.diplompractice.backendyakhno.enums.Role;

@Data
@NoArgsConstructor
public class LoginUser {
    private String username;
    private String password;
    private Role role;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User toUser(String passwordEncode){
        return User.builder()
                .username(username)
                .password(passwordEncode)
                .role(Role.ROLE_ADMIN)
                .build();
    }
}
