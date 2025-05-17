package com.killadeco.killadeco.dtos.user;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.UUID;

public record UpdateUserDto(
        @Schema(description = "ID único del usuario", example = "a81aa36d-bb7b-486d-a732-90af8c4edf9d")
        @NotNull
        UUID userId,
        @Schema(description = "Nombre de usuario", example = "Luciano Molina")
        @Pattern(
                regexp = "^(?=\\S*[a-zA-ZÀ-ÿ])(?=(?:\\S*\\s*){3,})[a-zA-ZÀ-ÿ\\s'-]+$",
                message = "El nombre de usuario debe tener al menos 3 letras y puede incluir espacios, apóstrofes o guiones"
        )
        String username,
        @Schema(description = "Correo electrónico del usuario", example = "luciano@gmail.com")
        @Pattern(
                regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$",
                message = "El correo electrónico debe ser válido y contener un dominio correcto"
        )
        @Email(message = "El correo electrónico debe ser valido, utilizando ´@´")
        String email,
        @Schema(description = "Contacto del usuario", example = "+54 351-5846563")
        @Pattern(
                regexp = "^(\\+\\d{1,3}\\s?)?\\d{9,15}$",
                message = "El contacto debe ser un número de teléfono válido en formato internacional"
        )
        String contact,
        @Schema(description = "Contraseña del usuario", example = "12345678Pro+")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
                message = """
                        La contraseña debe tener al menos 8 caracteres,
                        contener al menos un dígito, una letra minúscula, una letra mayúscula,
                        un carácter especial (@#$%^&+=) y no debe tener espacios."""
        )
        String password,
        @Schema(description = "Enviar notificaciones por correo", example = "true")
        Boolean wantsEmailNotifications
) implements Serializable {
}
