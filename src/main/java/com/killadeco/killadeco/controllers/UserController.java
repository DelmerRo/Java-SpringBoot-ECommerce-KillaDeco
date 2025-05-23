package com.killadeco.killadeco.controllers;

import com.killadeco.killadeco.dtos.ExtendedBaseResponse;
import com.killadeco.killadeco.dtos.user.*;
import com.killadeco.killadeco.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Users", description = "Gestionar todos los End-Points de usuarios. (RESTRINGIDO)")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Actualizar/Cargar la imagen de un usuario",
            description = "Permite actualizar la imagen de perfil de un usuario proporcionado su ID o carga una nueva imagen, " +
                    " ademas si esta ya tiene, borra la anterior y la actualiza."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Imagen actualizada exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExtendedBaseResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error al actualizar la imagen.",
                    content = @Content
            )
    })
    @PostMapping(value = "/images/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ExtendedBaseResponse<String>> updateUserImage(@Valid @ModelAttribute UpDateImagesUserDto dto) {
        ExtendedBaseResponse<String> datos = userService.upDateImagesUser(dto);
        return ResponseEntity.status(201).body(datos);
    }

    @Operation(
            summary = "Buscar usuario por ID",
            description = "Obtiene los datos de un usuario según su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error al buscar el usuario.",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedBaseResponse<UserDto>> findUserById(@PathVariable UUID id) {
        ExtendedBaseResponse<UserDto> response = userService.findUserById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Actualizar datos de un usuario",
            description = "Permite actualizar los datos de un usuario proporcionado su ID y los nuevos valores."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario actualizado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdatedUserDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error al actualizar los datos del usuario.",
                    content = @Content
            )
    })
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExtendedBaseResponse<UpdatedUserDto> updateUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

    @Operation(
            summary = "Actualizar el Rol de un usuario, roles: ( SUPER_ADMIN, ADMIN_DE_CONTENIDO, USUARIO, CRM)",
            description = "Permite actualizar los datos de un usuario proporcionado su ID y su Rol."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario actualizado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRolDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error al actualizar los datos del usuario.",
                    content = @Content
            )
    })
    @PutMapping(value = "/update-rol", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExtendedBaseResponse<UserRolDto> updateUserRolDto(@Valid @RequestBody ChangeUserRoleDto changeUserRoleDto) {
        return userService.changeUserRole(changeUserRoleDto);
    }

    @Operation(summary = "Listar todos los usuarios",
            description = "Permite recuperar una lista de todos los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Comentarios recuperados exitosamente.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExtendedBaseResponse.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = {@Content})
    })
    @GetMapping("/list")
    public ExtendedBaseResponse<List<UserDto>> getAllUsers() {
        return userService.userLists();
    }

    @Operation(summary = "Listar todos los usuarios activos",
            description = "Permite recuperar una lista de todos los usuarios que estén activos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios activos recuperados exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExtendedBaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = @Content)
    })
    @GetMapping("/list-active")
    public ExtendedBaseResponse<List<UserDto>> getAllActiveUsers() {
        return userService.userListsActive();
    }

    @Operation(summary = "Cambiar el estado de un usuario ( true / false )",
            description = "Permite activar o desactivar un usuario proporcionándole el ID y el nuevo estado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del usuario actualizado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExtendedBaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = @Content)
    })
    @PutMapping("/change-status")
    public ExtendedBaseResponse<UserDto> changeUserStatus(@Valid @RequestBody UserStatusRequest data) {
        return userService.changeUserStatus(data);
    }

    @Operation(summary = "Eliminar un usuario por ID",
            description = "Permite eliminar un usuario dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExtendedBaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ExtendedBaseResponse<String> deleteUser(@PathVariable UUID id) {
        return userService.deleteUserById(id);
    }
}
