package com.killadeco.killadeco.dtos.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Setter
@Getter
public class UpDateImagesUserDto {

    @NotNull(message = "La 'imagen' no puede estar vacía.")
    private MultipartFile image;

    @Schema(description = "Ingrese el ID del Usuario", example = "d96d6e88-4ade-4f62-98d7-235ea23f6f2a")
    @NotNull(message = "El 'ID del usuario' no puede estar vacío.")
    private UUID userId;
}