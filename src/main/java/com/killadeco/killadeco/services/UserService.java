package com.killadeco.killadeco.services;

import com.killadeco.killadeco.dtos.ExtendedBaseResponse;
import com.killadeco.killadeco.dtos.user.*;

import java.util.List;
import java.util.UUID;

public interface UserService {
    ExtendedBaseResponse<String> upDateImagesUser(UpDateImagesUserDto upDateImagesUser);

    ExtendedBaseResponse<UserDto> findUserById(UUID id);

    ExtendedBaseResponse<UpdatedUserDto> updateUser(UpdateUserDto updateUserDto);

    ExtendedBaseResponse<UserRolDto> changeUserRole(ChangeUserRoleDto data);

    ExtendedBaseResponse<List<UserDto>> userLists();

    ExtendedBaseResponse<List<UserDto>> userListsActive();

    ExtendedBaseResponse<UserDto> changeUserStatus(UserStatusRequest data);

    ExtendedBaseResponse<String> deleteUserById(UUID id);

    UUID getUserIdByEmail(String email);
}
