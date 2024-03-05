package org.diplompractice.backendyakhno.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    String token;
    String message;
}
