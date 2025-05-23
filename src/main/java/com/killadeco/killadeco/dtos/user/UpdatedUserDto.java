package com.killadeco.killadeco.dtos.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.UUID;

public record UpdatedUserDto(
        @Schema(description = "ID único del usuario", example = "d96d6e88-4ade-4f62-98d7-235ea23f6f2a")
        UUID userId,
        @Schema(description = "Nombre de usuario", example = "LucianoMO")
        String username,
        @Schema(description = "Correo electrónico del usuario", example = "lucianoFront23@gmail.com")
        String email,
        @Schema(description = "Contacto del usuario", example = "+3515846563")
        String contact,
        @Schema(description = "Contraseña del usuario", example = "123456780Pro+ (No Encriptado)")
        String password
) implements Serializable {
}
