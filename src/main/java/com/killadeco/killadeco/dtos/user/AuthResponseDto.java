package com.killadeco.killadeco.dtos.user;

import com.killadeco.killadeco.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.UUID;

public record AuthResponseDto(
        @Schema(description = "ID del usuario", example = "d96d6e88-4ade-4f62-98d7-235ea23f6f2a")
        UUID id,

        @Schema(description = "Nombre de usuario", example = "Florencia Galeassi")
        String username,

        @Schema(description = "Token de autenticación JWT", example = "eyJhbGciOiJIUzUxMiJ9...")
        String token,

        @Schema(description = "Rol del usuario", example = "USUARIO")
        User.Role role,

        @Schema(description = "Link de la imagen", example = "https://example.com/profile.jpg")
        String userImage
) implements Serializable {
}
