package tech.dev.eVoyageBackend.utils.dto;

import lombok.Data;
import lombok.Getter;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods based on the fields
public class LoginDto {
    private String emailOrPhone;
    private String password;

    // Getters et Setters
}