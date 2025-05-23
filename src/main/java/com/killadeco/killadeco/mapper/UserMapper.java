package com.killadeco.killadeco.mapper;

import com.killadeco.killadeco.dtos.user.AuthResponseDto;
import com.killadeco.killadeco.dtos.user.UpdatedUserDto;
import com.killadeco.killadeco.dtos.user.UserDto;
import com.killadeco.killadeco.dtos.user.UserRolDto;
import com.killadeco.killadeco.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserDto userDto);

    @Mapping(target = "username", expression = "java(mapUsername(user))")
    UserDto toDto(User user);

    List<UserDto> entityListToDtoList(List<User> users);

    @Mapping(source = "id", target = "userId")
    @Mapping(target = "username", expression = "java(mapUsername(user))")
    UpdatedUserDto toUpdatedUser(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "username", expression = "java(mapUsername(user))")
    @Mapping(target = "token", ignore = true)
    AuthResponseDto toAuthResponse(User user);

    @Mapping(target = "username", expression = "java(mapUsername(user))")
    UserRolDto toUserRolDto(User user);

    default String mapUsername(User user) {
        return user.getName();

    }
}